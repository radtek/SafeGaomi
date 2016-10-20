package com.hisign.publicsafety.controller.ccp;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hisign.publicsafety.controller.BaseController;
import com.hisign.publicsafety.entity.Roles;
import com.hisign.publicsafety.entity.ccp.ClueInfo;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataRequest;
import com.hisign.publicsafety.pulgin.mybatis.plugin.DataResponse;
import com.hisign.publicsafety.service.DicService;
import com.hisign.publicsafety.service.RolesService;
import com.hisign.publicsafety.service.ccp.ClueInfoService;
import com.hisign.publicsafety.util.Common;
import com.hisign.publicsafety.util.DateUtils;
import com.hisign.publicsafety.util.LogUtil;
import com.hisign.publicsafety.web.beans.CsrfTokenManager;

/**
 * 线索信息查询controller
 * @author hotdog
 * 2015-11-23
 *
 */
@Controller
@RequestMapping("/ccp/clueInfo/")
public class ClueInfoController extends BaseController{
	
	@Inject
	private ClueInfoService clueInfoService;
	@Inject
	private RolesService roleService;
	@Inject
	private DicService dicService; 
	
	/**
	 * 进入线索信息查询模块
	 * @param model
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		
		JSONArray ja = new JSONArray();
		Roles role = new Roles();
		role.setEnable("1");
		List<Roles> roleList = roleService.queryAll(role);
		if (roleList != null) {
			for (Roles r : roleList) {
				JSONObject jobj = new JSONObject();
				jobj.put("id", r.getId());
				jobj.put("name", r.getName());
				ja.add(jobj);
			}
		}

		String unitJson;
		try {
			unitJson = dicService.getUnitJson();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		/**
		 * 添加token验证
		 */
		model.addAttribute("csrf", CsrfTokenManager.getToken());

		model.addAttribute("ja", ja.toString());
		model.addAttribute("dicJsonArr", unitJson);
		
		return  Common.CCP_PATH + "/clueInfo/clueInfoList";
	}
	
	/**
	 *  打开百度地图(单挑线索展示)
	 * @param model
	 * @param lon 经度
	 * @param lat 纬度
	 * @return
	 */
	@RequestMapping("showBMap")
	public String showBMap(Model model, String id) {
		
		try {
			ClueInfo obj = clueInfoService.getById(id);
			if (obj != null) {
				//加载线索图片信息
				setCluePhotoCount(obj);
				
				model.addAttribute("lon", obj.getLon());
				model.addAttribute("lat", obj.getLat());
				model.addAttribute("proViderPhoneNum",obj.getProViderPhoneNum());
				model.addAttribute("firstPhotoSrc", obj.getFirstPhotoSrc().replaceAll("\\\\", "/"));
				model.addAttribute("collectDate", DateUtils.parse2String(obj.getCollectDate(), DateUtils.YMDHMCCP));
				model.addAttribute("id", id);
				model.addAttribute("csrf", CsrfTokenManager.getToken());
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  Common.CCP_PATH + "/clueInfo/showBMap";
	}
	
	/**
	 *  打开百度地图(list)
	 * @param model
	 * @param lon 经度
	 * @param lat 纬度
	 * @return
	 */
	@RequestMapping("showBMapForList")
	public String showBMapForList(Model model, ClueInfo t) {
		try {
			JSONArray clueListJSONArr = new JSONArray(); 
			List<ClueInfo> resList = clueInfoService.queryAll(t);
			if (resList != null && resList.size() > 0) {
				for (ClueInfo obj : resList) {
					//加载线索图片信息
					setCluePhotoCount(obj);
					
					JSONObject jobj = new JSONObject();
					jobj.put("lon", obj.getLon());
					jobj.put("lat", obj.getLat());
					jobj.put("id", obj.getId());
					jobj.put("proViderPhoneNum", obj.getProViderPhoneNum());
					jobj.put("collectDate", DateUtils.parse2String(obj.getCollectDate(), DateUtils.YMDHMCCP));
					if(obj.getFirstPhotoSrc()==null || "".equals(obj.getFirstPhotoSrc())){
						jobj.put("firstPhotoSrc", "");
					}else{
						jobj.put("firstPhotoSrc", obj.getFirstPhotoSrc().replaceAll("\\\\", "/"));
					}
					clueListJSONArr.add(jobj);
				}
			}
			model.addAttribute("listJsonArr", clueListJSONArr.toString());
			model.addAttribute("csrf", CsrfTokenManager.getToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  Common.CCP_PATH + "/clueInfo/showBMapForList";
	}
	
	/**
	 * 查询线索总数（首页用）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getClueCount")
	public Map getClueCount () {
		Map map = new HashMap<String, Object>();
		try {
			int clueCount = clueInfoService.queryCount(new ClueInfo());
			map.put("flag", "success");
			map.put("clueCount", clueCount);
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
		}
		return map;
	}
	
	/**
	 * 查询上传照片总数（首页用）
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getPhotoCount")
	public Map getPhotoCount () {
		Map map = new HashMap<String, Object>();
		try {
			int photoCount = 0;
			
			String basePath = request.getSession().getServletContext().getRealPath("/upload");
			File file = new File(basePath);
			if (file.isDirectory()) {
				//获取图片总目录下 所有注册用户的文件夹数量
				File[] providerDirList = file.listFiles();
				for (int i = 0; i < providerDirList.length; i++) {
				   if (providerDirList[i].isDirectory()) {
					   //获取用户文件夹
					   File providerDir = providerDirList[i];
					   //第二层 获取用户目录下所有提供的线索的文件夹
					   File[] clueDirList = providerDir.listFiles();
					   for (int j = 0; j < clueDirList.length; j++) {
						   //获取线索文件夹
						   File clueFile = clueDirList[j];
						   //第三层 获取所有线索文件夹下的所有图片文件
						   File[] photoFileList = clueFile.listFiles();
						   photoCount += photoFileList.length;
					   }
				   }
				}
			}
			map.put("flag", "success");
			map.put("photoCount", photoCount);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "false");
		}
		return map;
	}
	
	/**
	 * jgrid表格查询
	 * @param model
	 * @param clueInfo
	 * @param dataRequest
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public DataResponse<ClueInfo> query (Model model, ClueInfo t, DataRequest dataRequest) {
		
		int page=dataRequest.getPage();
		int rows=dataRequest.getRows();
		pageView = clueInfoService.query(getPageView(String.valueOf(page),String.valueOf(rows)), t);
		List<ClueInfo> resList = pageView.getDataResponse().getRows();
		if (resList != null && resList.size() > 0) {
			for (ClueInfo obj : resList) {
				setCluePhotoCount(obj);
			}
		}
		return pageView.getDataResponse();
	}
	
	/**
	 * 查看线索详细信息
	 * @param model
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("view")
	public Map<String, Object> view (String id) {
		
		String basePath = request.getSession().getServletContext().getRealPath("/upload");
		String phtotFilePath = "";
		List photoSrcList = null;
		Map map = new HashMap<String, Object>();
		try {
			ClueInfo obj = clueInfoService.getById(id);
			if (obj != null) {
				
				photoSrcList = new ArrayList();
				//获取该线索照片路径List
				
				//线索照片文件路径
				phtotFilePath =  File.separator + obj.getProviderId() + File.separator + obj.getCollectNo();
				File file = new File(basePath + phtotFilePath);
				if (file.isDirectory()) {
					String photoPath = "";
					String[] filelist = file.list();
					obj.setCluePhotoCount(filelist.length);
					
					if (filelist.length > 0) {
						//获取每张照片的路径，存入list中
						for (int i = 0; i < filelist.length; i++) {
							File readfile = new File(phtotFilePath + "\\" + filelist[i]);
							photoPath = File.separator + "upload" + phtotFilePath + File.separator + readfile.getName();
							photoSrcList.add(photoPath);
						}
						obj.setPhotoSrcList(photoSrcList);
					}
				} 
				
				map.put("clueInfo", obj);
			} else {
				map.put("flag", "false");
				map.put("content", "未找到相关线索信息");
			}
			map.put("flag", "success");
		} catch (Exception e) {
			LogUtil.errStack2Log4j(e);
			map.put("flag", "false");
			map.put("content", "查询异常");
		}
		return map;
	}
	
	/**
	 * 获取该条线索的照片数量，并赋值用于列表展示的图片url
	 * @param obj
	 */
	private void setCluePhotoCount (ClueInfo obj) {
		String basePath = request.getSession().getServletContext().getRealPath("/upload");
		//线索照片文件路径
		String phtotFilePath =  File.separator + obj.getProviderId() + File.separator + obj.getCollectNo();
		File file = new File(basePath + phtotFilePath);
		List photoSrcList = null;
		if (file.isDirectory() && file.list().length > 0) {
			
			photoSrcList = new ArrayList();
			String photoPath = "";
			String[] filelist = file.list();
			obj.setCluePhotoCount(filelist.length);
			
			File readfile = new File(phtotFilePath + "\\" + filelist[0]);
			String firstPhotoSrc = photoPath = File.separator + "upload" + phtotFilePath + File.separator + readfile.getName();
			obj.setFirstPhotoSrc(firstPhotoSrc);
			
			//获取每张照片的路径，存入list中
			for (int i = 1; i < filelist.length; i++) {
				File readfile1 = new File(phtotFilePath + "\\" + filelist[i]);
				photoPath = File.separator + "upload" + phtotFilePath + File.separator + readfile1.getName();
				photoSrcList.add(photoPath);
			}
			obj.setPhotoSrcList(photoSrcList);
		}
	}
	
	/**
	 * 获取图片流，转base64字符串
	 * @param obj
	 */
	public void getFirstPhotoBase64Str (ClueInfo obj) {
		try {
			//读取文件流 将图片转为base64
			String basePath = request.getSession().getServletContext().getRealPath("/");
			String firstPhotoAbsPath = basePath + obj.getFirstPhotoSrc();
			File file = new File(firstPhotoAbsPath);
			FileInputStream ins = new FileInputStream(file);
			byte[] photoByte = new byte[(int)file.length()];
			ins.read(photoByte);
			ins.close();
			String photoBase64Str = org.apache.commons.codec.binary.Base64.encodeBase64String(photoByte);
			obj.setFirstPhotoBase64Str(photoBase64Str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
