<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<meta charset="utf-8" />
<title>O2O数据侦查平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="MobileOptimized" content="320">
</head>
<body class="page-header-fixed page-sidebar-closed">
	<%@ include file="../common/meun.jsp"%>
	<div class="clearfix"></div>
	<div class="container">
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
		
			<div class="col-md-12 margin-top-10">
		      <div class="portlet">
		        <div class="portlet-title">
		          <div class="caption"> <i class="fa fa-info-circle"></i>委托编号:${entrustNo} </div>
		        </div>
		      </div>
		    </div>
			<!-- BEGIN SIDEBAR -->
			<!-- END SIDEBAR -->
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
			
			<c:if test="${process=='result'}">
	    	<div class="col-md-12">
				<div class="portlet">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-table"></i>委托反馈信息
						</div>
						<div class="tools add-tools">
							<a href="#"  onclick="exportExcel()" class="btn btn-xs btn-link">全部导出 <i
								class="fa fa-share-square-o"></i>
							</a>
							<input type="hidden" id="entrustNo" value="${entrustNo}"/>
						</div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<li class="active"><a  href="#feedback_tab_0" data-toggle="tab">注册信息</a></li>
								<li ><a  href="#feedback_tab_1" data-toggle="tab">登录信息</a></li>
								<li><a  href="#feedback_tab_2" data-toggle="tab">交易记录</a></li>
								<li><a href="#feedback_tab_3" data-toggle="tab">账户明细</a></li>
								<li><a href="#feedback_tab_4" data-toggle="tab">转账明细</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="feedback_tab_0">
									<div class="table-scrollable">
									  <table   id="registerTable"></table>
			                          <div id="registergridPager"></div>
									</div>
								</div>
								
								<div class="tab-pane" id="feedback_tab_1">
								   <div class="table-scrollable">
								    <table    id="loginHistoryTable"></table>
			                          <div id="logingridPager"></div>
			                        </div>
								</div>
								
								<div class="tab-pane" id="feedback_tab_2">
									<div class="table-scrollable">
							             <table   id="transactionRecordsTable"></table>
			                             <div id="transactionRecordsgridPager"></div>
									</div>
								</div>
								<div class="tab-pane" id="feedback_tab_3">
									<div class="table-scrollable">
							             <table  id="accountDetailTable"></table>
			                             <div id="accountDetailgridPager"></div>
									</div>
								</div>
								<div class="tab-pane" id="feedback_tab_4">
									<div class="table-scrollable">
										 <table    id="transferDetailTable"></table>
			                             <div id="transferDetailgridPager"></div>
									</div>
								</div>
							</div>
							<!-- END SAMPLE TABLE PORTLET-->
						</div>
					</div>
				</div>
			</div>	
			</c:if>    
	    
	    	<div class="col-md-12">
				<!-- BEGIN SAMPLE TABLE PORTLET-->
				<div class="portlet">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-table"></i>委托信息
						</div>
						<div class="tools"></div>
					</div>
					<div class="portlet-body">
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab_0" data-toggle="tab">委托信息</a>
								</li>
								<li><a href="#tab_1" data-toggle="tab">案件信息</a></li>
								<li><a href="#tab_2" data-toggle="tab">需侦查信息</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active form-horizontal form-bordered"
									id="tab_0">
									<div class="form-body">
									 <div class="form-group">
											<div class="col-md-2 text-right gray">委托编号</div>
											<div class="col-md-4">${entrustData.entrustInfo.entrustNo}
											 <input type="hidden" id="entrustNo" value="${entrustData.entrustInfo.entrustNo}">
											</div>
											<div class="col-md-2 text-right gray">委托序号</div>
											<div class="col-md-4">第${entrustData.entrustInfo.entrustNumber}次</div>
										</div>
										<div class="form-group">
											<div class="col-md-2 text-right gray">办案单位所在地</div>
											<div class="col-md-4">
											 <c:forEach items="${province}" var="bean">
											     <c:if test="${bean.dicKey==entrustData.entrustInfo.provinceCode}">
											         ${bean.dicName}
											     </c:if>
											 </c:forEach>
											 <c:forEach items="${city}"  var="bean">
											   <c:if test="${bean.dicKey==entrustData.entrustInfo.cityCode}">
											          ${bean.dicName}
											    </c:if>
											 </c:forEach>
											</div>
											<div class="col-md-2 text-right gray">办案单位名称</div>
											<div class="col-md-4">${entrustData.entrustInfo.rev7}</div>
										</div>
										<div class="form-group">
											<div class="col-md-2 text-right gray">办案人</div>
											<div class="col-md-4">${entrustData.entrustInfo.personName}</div>
											<div class="col-md-2 text-right gray">办案人联系电话</div>
											<div class="col-md-4">${entrustData.entrustInfo.telephone}</div>
										</div>
										<div class="form-group">
											<div class="col-md-2 text-right gray">办案人职务</div>
											<div class="col-md-4">${entrustData.entrustInfo.title}</div>
											<div class="col-md-2 text-right gray">是否来人调取</div>
											<div class="col-md-4">
											 <c:if test="${entrustData.entrustInfo.istake=='1'}">
											      是
											 </c:if>
											  <c:if test="${entrustData.entrustInfo.istake!='1'}">
											      否
											 </c:if>
											</div>
										</div>
										<c:if test="${entrustData.entrustInfo.istake=='1'}">
										 <div class="form-group">
											<div class="col-md-2 text-right gray">来访人姓名</div>
											<div class="col-md-4">${entrustData.entrustInfo.visitorName}</div>
											<div class="col-md-2 text-right gray">来访人联系方式</div>
											<div class="col-md-4">${entrustData.entrustInfo.visitorTelephone}</div>
										 </div>
										 <div class="form-group">
											<div class="col-md-2 text-right gray">来访人单位</div>
											<div class="col-md-4">${entrustData.entrustInfo.visitorUnit}</div>
											<div class="col-md-2 text-right gray">来访人职务</div>
											<div class="col-md-4">${entrustData.entrustInfo.visitorTitle}</div>
										 </div>
										</c:if>
										
										<div class="form-group">
											<div class="col-md-2 text-right gray">案件办理阶段</div>
											<div class="col-md-4">
											  <c:forEach items="${caseStage}" var="bean">
											     <c:if test="${bean.dicKey==entrustData.entrustInfo.caseStage}">
											        ${bean.dicName}
											     </c:if>
											  </c:forEach>
											</div>
											<div class="col-md-2 text-right gray">是否需要回函</div>
											<div class="col-md-4">
											  <c:if test="${entrustData.entrustInfo.isReply=='1'}">
											      是
											 </c:if>
											  <c:if test="${entrustData.entrustInfo.isReply!='1'}">
											      否
											 </c:if>
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-2 text-right gray">是否共享案件信息</div>
											<div class="col-md-4">
											   <c:if test="${entrustData.entrustInfo.isShareCaseinfo=='1'}">
											       是
											 </c:if>
											  <c:if test="${entrustData.entrustInfo.isShareCaseinfo!='1'}">
											       否
											 </c:if>
											</div>
											<div class="col-md-2 text-right gray">是否共享查询结果</div>
											<div class="col-md-4">
											    <c:if test="${entrustData.entrustInfo.isShareQueryresult=='1'}">
											       是
											 </c:if>
											  <c:if test="${entrustData.entrustInfo.isShareQueryresult!='1'}">
											       否
											 </c:if>
											</div>
										</div>
										<div class="form-group last">
											<div class="col-md-2 text-right gray">附件文书</div>
											<div class="col-md-4">
											<c:if test="${fsize=='1'}">
											  <c:forEach items="${entrustData.attachments}" var="bean">
											  <c:if test="${bean.rev1!='0'}">
											    <a href="javascript:void(0)" onclick="downFilebyId('${bean.id}')">${bean.fileName}</a>&nbsp;&nbsp;
											  </c:if>
											   <c:if test="${bean.rev1=='0'}">
											     ${bean.fileName}<span ><font color="red">(文件不存在，请重新上传附件文书)</font> </span>
											   </c:if>
											  </c:forEach>
											 </c:if>
											 <c:if test="${fsize!='1'}">
											   <span ><font color="red">文件不存在，请重新上传附件文书</font> </span>
											 </c:if>
											</div>
											<div class="col-md-2 text-right gray">备注</div>
											<div class="col-md-4">
											  ${entrustData.entrustInfo.memo}
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane form-horizontal form-bordered" id="tab_1">
									<div class="table-responsive">
										<div class="form-body">
											<div class="form-group">
												<div class="col-md-2 text-right gray">案件名称</div>
												<div class="col-md-4"> ${entrustData.caseInfo.caseName}</div>
												<div class="col-md-2 text-right gray">案件编号</div>
												<div class="col-md-4">${entrustData.caseInfo.caseNo}</div>
											</div>
											<div class="form-group">
												<div class="col-md-2 text-right gray">案发时间</div>
												<div class="col-md-4">${entrustData.caseInfo.caseOccurDate}</div>
												<div class="col-md-2 text-right gray">涉案品牌</div>
												<div class="col-md-4">${entrustData.caseInfo.brand}</div>
											</div>
											<div class="form-group">
												<div class="col-md-2 text-right gray">案件金额</div>
												<div class="col-md-4">${entrustData.caseInfo.caseMoneyAmout}</div>
												<div class="col-md-2 text-right gray">案件类型</div>
												<div class="col-md-4">
												   ${pdicName}
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-2 text-right gray">简要案情</div>
												<div class="col-md-10 "><div class="autodiv">${entrustData.caseInfo.caseInfo} </div></div>
											</div>
											<div class="form-group">
												<div class="col-md-1 text-right gray"></div>
												<div class="col-md-10">
												 <div style="padding-left:8px;"><font style="font-weight:bold">受害人信息 </font></div>
												 <div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div> 
													<table class="table table-hover">
														<thead>
															<tr>
																<th>序号</th>
																<th>姓名</th>
																<th>身份证</th>
																<th>手机</th>
																<th>淘宝账号</th>
																<th>支付宝账号</th>
																<th>所在地址</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${entrustData.victimInfo}" var="bean" varStatus="status">
					                                        <tr >
					                                        <td> <span>${status.index+1}</span></td>
					                                        <td> ${bean.victimName}</td>
					                                        <td> ${bean.victimCardNo}</td>
					                                        <td> ${bean.victimTelephone}</td>
					                                        <td> ${bean.victimTaobaoNo}</td>
					                                        <td> ${bean.victimAlipay}</td>
					                                        <td> ${bean.victimAddress} </td>
					                                        </tr>
					                                     </c:forEach>
														</tbody>
													</table>
												</div>
											</div>
											<div class="form-group last">
												<div class="col-md-1 text-right gray"> </div>
												<div class="col-md-10">
												<div style="padding-left:8px;"><font style="font-weight:bold">嫌疑人信息</font> </div>
												<div style="margin:0;padding:0; width:100%;height:1px;background-color:#999;overflow:hidden;"></div> 
													<table class="table table-hover">
														<thead>
															<tr>
																<th>序号</th>
																<th>姓名</th>
																<th>身份证</th>
																<th>手机</th>
																<th>淘宝账号</th>
																<th>支付宝账号</th>
																<th>QQ</th>
																<th>电子邮件</th>
															</tr>
														</thead>
														<tbody>
														  <c:forEach items="${entrustData.suspectInfo}"  var="bean" varStatus="status">
						                                        <tr >
						                                        <td> <span>${status.index+1}</span></td>
						                                        <td> ${bean.suspectName}</td>
						                                        <td> ${bean.suspectCardNo}</td>
						                                        <td> ${bean.suspectTelephone} </td>
						                                        <td> ${bean.suspectTaobaoNo}</td>
						                                        <td> ${bean.suspectAlipay}</td>
						                                        <td> ${bean.suspectQq}</td>
						                                        <td> ${bean.suspectEmail}</td>
						                                        </tr>
						                                  </c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane form-horizontal form-bordered" id="tab_2">
									<div class="table-responsive">
										<div class="form-body">
											<div class="form-group">
												<div class="col-md-2 text-right gray">查询类型</div>
												<div class="col-md-10 checkbox-list">
												 ${queryStr}
												</div>
											</div>
											<div class="form-group last">
												<div class="col-md-2 text-right gray">协查信息</div>
												<div class="col-md-10">
													<table class="table table-hover">
														<thead>
															<tr>
																<th>序号</th>
																<th>类型</th>
																<th>内容</th>
																<th>说明</th>
															</tr>
														</thead>
														<tbody>
														 <c:forEach items="${entrustData.investigateInfo}"  var="bean" varStatus="status">
						                                        <tr id='investigateInfo${status.index}'>
						                                        <td> <span>${status.index+1}</span></td>
						                                        <td>
							                                        <c:forEach items="${xcxxList}" var="bean1">
							                                           <c:if test="${bean.investType==bean1.dicKey}">
							                                           ${bean1.dicName}
							                                           </c:if>
						                                            </c:forEach> 
						                                          </td>
						                                         <td> ${bean.investContent} </td>
						                                         <td> ${bean.investDesc} </td>
						                                        </tr>
						                                  </c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- END SAMPLE TABLE PORTLET-->
						</div>
					</div>
				</div>
			</div>
	    
	    
				<div class="clearfix"></div>
				<div class="form-actions fluid">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-offset-4 col-md-9">
								<a href="javascript:;" 
									onclick="historyPage()" class="btn btn-default button-previous margin-right-10"> <i
									class="fa fa-angle-left"></i> 返回 </a> 
									<c:if test="${process=='result'}"> 
										<a class="btn btn-primary margin-right-10" data-toggle="modal"
										href="#confirm"> 确认 </a>
									</c:if>
									<c:if test="${process=='submit'}"> 
										<a class="btn btn-primary margin-right-10" data-toggle="modal"
										href="#accept"> 审核通过 </a>
										<a class="btn btn-primary margin-right-10" data-toggle="modal"
										href="#reject"> 审核不通过 </a>
									</c:if>
							</div>
						</div>
					</div>
				</div>
				<!-- END SAMPLE TABLE PORTLET-->
				<div class="modal fade" id="reject" tabindex="-1" role="basic"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">审核信息不通过</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label for="exampleInputEmail1">不通过原因</label>
									<textarea id="maxlength_textarea" class="form-control"
										maxlength="225" rows="2"></textarea>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" id="notpassbutton" onclick="checkEntrust('notpass')" class="btn btn-primary">确定</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<div class="modal fade" id="accept" tabindex="-1" role="basic"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">审核通过</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label>确认审核通过该委托信息？</label>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" id="passbutton" onclick="checkEntrust('pass')" class="btn btn-primary">确定</button>
							</div>	
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				
				<div class="modal fade" id="confirm" tabindex="-1" role="basic"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true"></button>
								<h4 class="modal-title">反馈确认</h4>
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label>确认该委托反馈信息？</label>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" id="feedbackbutton" onclick="checkEntrust('feedback')" class="btn btn-primary">确定</button>
							</div>	
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				
			</div>
		</div>
	</div>
</body>
<%@ include file="entrust_checkdetail_js.jsp"%>
</html>