package com.hisign.publicsafety.controller.entrust;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.druid.util.StringUtils;
import com.hisign.publicsafety.entity.Dic;
import com.hisign.publicsafety.viewModel.multselect.MultSelectDataModel;
/**
 * controller公用方法
 * @author fankai
 *
 */
public class ControllerUtil {
  
	/**
	 * 查询当前字典的所有子节点组成字符串
	 * @param dlist
	 * @param dickey
	 * @return
	 */
	public static StringBuffer findAllChildrenCode(List<Dic> dlist,String dickey){
		StringBuffer code=new StringBuffer();
		if(!StringUtils.isEmpty(dickey)){
			for(Dic dic:dlist){
				if(!StringUtils.isEmpty(dic.getDicParentKey())){
				 if(dic.getDicParentKey().equals(dickey)){
					code.append(dic.getDicKey()).append(",");
					List<Dic> rlist= ControllerUtil.getSelectList(dlist, dic.getDicKey());
					if(rlist.size()>0){
						code.append(findAllChildrenCode(dlist,dic.getDicKey()));
					}
				 }
			  }
			}
		}
		return code;
	}
	
	/**
	 * 拼接当前字典值和上一级字典值
	 * @param dlist 字典集合
	 * @param dickey 字典key
	 * @return
	 */
	public static  String findCaseTypeZn(List<Dic> dlist,String dickey,String caseType){
		String curCaseType="";
		String pcode="";
		if(dlist!=null){
			for(Dic dic:dlist){
				if(dickey.equals(dic.getDicKey())){
					pcode=dic.getDicParentKey();
					curCaseType=dic.getDicName();
					if(!StringUtils.isEmpty(pcode)){
					   caseType="-"+curCaseType+caseType;
					}else{
					   caseType=curCaseType+caseType;
					}
					if(!StringUtils.isEmpty(pcode)){
					   caseType=findCaseTypeZn(dlist, pcode, caseType);
					}
					break;
				}
			}
			
		}
		return caseType;
	}
	
    /**
     * 获得下级节点
     * @param list
     * @param dicroot
     * @return
     */
	public static  List <Dic> getSelectList(List<Dic> list,String dicroot){
		List<Dic> rlist=new ArrayList<Dic>();
		if (list!=null && list.size()>0){
			for (Dic dic:list){
				if (!StringUtils.isEmpty(dic.getDicParentKey())){
					if (dic.getDicParentKey().equals(dicroot)){
						rlist.add(dic);
					}
				}
			}
		}
		return rlist;
	}
	
	/**
	 * 获得当前节点的所有子节点
	 * @param CaseTypeProv
	 * @param dic
	 * @return
	 */
	public static MultSelectDataModel getAllChildrenNode(List<Dic> CaseTypeProv,Dic dic,MultSelectDataModel mnode){
		List<Dic> rlist=getSelectList(CaseTypeProv, dic.getDicKey());
		if(rlist.size()>0){
			List<MultSelectDataModel> nodes=new ArrayList<MultSelectDataModel>();
			for(Dic dicBean:rlist){
				MultSelectDataModel node=new MultSelectDataModel();
				node.setId(dicBean.getDicKey());
				node.setText(dicBean.getDicName());
				nodes.add(node);
				List<Dic> ldic=getSelectList(CaseTypeProv, dicBean.getDicKey());
				if(ldic.size()>0){
					getAllChildrenNode(CaseTypeProv, dicBean, node);
				}
			}
			mnode.setNodes(nodes);
		}
		return mnode;
	}
	
	/**
	 * 得到当前节点的中文名称
	 * @param list
	 * @param dicKey
	 * @return
	 */
	public static  String getName(List<Dic> list,String dicKey){
		if(!StringUtils.isEmpty(dicKey)){
			for(Dic dic:list){
				if(dic.getDicKey().equals(dicKey)){
					return dic.getDicName();
				}
			}
		}
		return "";
	}
}
