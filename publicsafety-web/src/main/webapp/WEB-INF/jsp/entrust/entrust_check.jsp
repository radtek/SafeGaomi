<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">

<meta name="current-res-id" content="RES_entrust_check"/>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<head>
<meta charset="utf-8"/>
<title>O2O数据侦查平台</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="" name="author"/>
<meta name="MobileOptimized" content="320">
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed page-sidebar-closed">
<%@ include file="../common/meun.jsp"%>
<!-- END HEADER -->
<div class="clearfix"> </div>
<div class="container"> 
  <!-- BEGIN CONTAINER -->
  <div class="page-container"> 
    <!-- BEGIN SIDEBAR --> 
    <!-- END SIDEBAR --> 
    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
      <div class="row">
        <form action="#"  method="post" class="form-horizontal" id="submit_form" >
          <div class="form-wizard">
            <div class="form-body">
            
            <div class="form-group">
	              <label class="col-md-offset-1 control-label col-md-2">案(事)件类型</label>
	              <div class="col-md-7 selected">
				    <input type="text"  class="form-control"  id="qcaseTypetreeinputview"   value="">
	                <input type="hidden" name="caseType"  id="caseType" value="">
	                 <div id="qcaseTypetree"  class="selectstyle">
				    <div class="select2-search selected">
	                      <input type="text" id="qcaseTypetreeinput-search" value=""></input>
	                </div>
				    <div id="qcaseTypetree-searchable" class="select2-results"></div>
				    <div class="treeview" id="qcaseTypetreesearch-output">
					  <ul class="select2-results"><li class="select2-no-results"></li></ul>
				    </div>
			      </div>
	              </div>
	             
			 </div>
              <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 案(事)件名称 </label>
                <div class="col-md-7">
                  <input type="text" class="form-control" id="caseName" name="caseName">
                  <input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 委托编号 </label>
                <div class="col-md-7">
                  <input type="text" class="form-control" id="entrustNo" name="entrustNo">
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 委托人</label>
                <div class="col-md-7">
                  <input type="text" class="form-control" id="personName" name="personName">
                </div>
              </div>
              
             <div class="form-group">
	              <label class="col-md-offset-1 control-label col-md-2">委托单位</label>
	              <div class="col-md-7 selected1">
				    <input type="text" class="form-control tags" id="unittreeinputview"  placeholder="" value="">
	                <input type="hidden" name="unit"  id="unit" value="">
	                <div id="unittree"  class="selectstyle">
				    <div class="select2-search selected1 ">
	                   <input type="text" id="unittreeinput-search" value=""></input>
	                </div>
				    <div id="unittree-searchable" class="select2-results"></div>
				    <div class="treeview" id="unittreesearch-output">
					 <ul class="select2-results"><li class="select2-no-results"></li></ul>
				    </div>
			     </div>
	              </div>
	              
			 </div>
			
              <!-- 
               <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2">委托单位 </label>
                <div class="col-md-6">
                 <select id="unit" name="unit" class="form-control select2" placeholder="" ></select>
                </div>
               </div>
               -->
               <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 委托时间 </label>
                <div class="col-md-7">
                  <div class="input-group">
                    <input type="text" id="entrustDateBegin" name="entrustDateBegin"  class="form-control date-set date form_datetime">
                    <span class="input-group-addon"> 至 </span>
                    <input type="text" id="entrustDateEnd" name="entrustDateEnd"  class="form-control date-set date form_datetime">
                  </div>
                </div>
              </div>
              <div class="form-group">
                <label class="col-md-offset-1 control-label col-md-2"> 委托状态</label>
                <div class="col-md-9">
                  <div class="btn-group" style="font-size: 8px" data-toggle="buttons" >
                   <c:forEach items="${processState}" var="bean" varStatus="status">
                   <c:if test="${bean.dicKey=='submit' || bean.dicKey=='result'}">
                        <label id="processState${status.index}" onclick="SelectStatus('processState','${status.index}');" name="processState" class="btn btn-success"  > 
                          <input type="checkbox" id="processStateinput${status.index}"  value="${bean.dicKey}" checked  name="processState" class="toggle"></input>
                          ${bean.dicName}
                        </label> 
                   </c:if>
                   <c:if test="${bean.dicKey=='processing'||  bean.dicKey=='notpass' ||  bean.dicKey=='finish' ||  bean.dicKey=='feedback'}">
                       <label id="processState${status.index}" onclick="SelectStatus('processState','${status.index}');" name="processState" class="btn btn-default active"  > 
                         <input type="checkbox" id="processStateinput${status.index}"  value="${bean.dicKey}" checked  name="processState" class="toggle"></input>
                         ${bean.dicName}
                       </label> 
                   </c:if>
                         
                    </c:forEach>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <div class="col-md-offset-3 col-md-9">
                 <a href="javascript:;" onclick="queryCheckList()" class="btn btn-primary margin-right-10"> 查询 <i class="fa fa-search "></i> </a>
                  <a href="javascript:;" onclick="resetFomrParam()" class="btn btn-default button-previous margin-right-10" >重置</a> 
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="col-md-12"> 
        <!-- BEGIN SAMPLE TABLE PORTLET-->
        <div class="portlet">
          <div class="portlet-title">
            <div class="caption"> <i class="fa fa-table"></i>委托列表 </div>
             <div  align="right">
              <a  class="btn btn-xs btn-link margin-right-10" id="confirmdiv" data-toggle="modal" href="#confirm"> 确认反馈 </a>
              <a  class="btn btn-xs btn-link margin-right-10" id="acceptdiv" data-toggle="modal" href="#accept"> 审核通过 </a>
              <a  class="btn btn-xs btn-link margin-right-10" id="rejectdiv"  data-toggle="modal" href="#reject"> 审核不通过 </a>
             </div>
            </div>
          <div class="portlet-body">
            <div class="table-responsive">
                  <table   id="checkTable"></table>
			      <div id="checkgridPager"></div>
            </div>
          </div>
      </div>
      <!-- END SAMPLE TABLE PORTLET--> 
    </div>
  </div>
  <!-- END SAMPLE TABLE PORTLET-->
			<div class="modal fade" id="reject" tabindex="-1" role="basic" aria-hidden="true">
					<div class="modal-dialog">
							<div class="modal-content">
									<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
											<h4 class="modal-title">审核信息不通过</h4>
									</div>
									<div class="modal-body">
											<div class="form-group">
													<label for="exampleInputEmail1">不通过原因</label>
													<textarea id="maxlength_textarea" class="form-control" maxlength="225" rows="2" ></textarea>
											</div>
									</div>
									<div class="modal-footer">
											<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
											<button id="checknotpassbutton"  type="button" class="btn btn-primary">确定</button>
									</div>
							</div>
							<!-- /.modal-content --> 
					</div>
					<!-- /.modal-dialog --> 
			</div>
			<div class="modal fade" id="accept" tabindex="-1" role="basic" aria-hidden="true">
								<div class="modal-dialog">
										<div class="modal-content">
												<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
														<h4 class="modal-title">审核通过</h4>
												</div>
												<div class="modal-body">
														<div class="form-group">
																<label  >是否确认审核通过所选委托信息？</label>
																
														</div>
												</div>
												<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
														<button id="checkpassbutton" type="button" class="btn btn-primary">确定</button>
												</div>
										</div>
										<!-- /.modal-content --> 
								</div>
								<!-- /.modal-dialog --> 
						</div>
			 <div class="modal fade" id="confirm" tabindex="-1" role="basic" aria-hidden="true">
								<div class="modal-dialog">
										<div class="modal-content">
												<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
														<h4 class="modal-title">确认通过</h4>
												</div>
												<div class="modal-body">
														<div class="form-group">
																<label>确认反馈所选信息？</label>
																
														</div>
												</div>
												<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
														<button id="checkconfirmbutton" type="button" class="btn btn-primary">确定</button>
												</div>
										</div>
										<!-- /.modal-content --> 
								</div>
								<!-- /.modal-dialog --> 
						</div>
		 	  <div class="modal fade" id="noConfirm" tabindex="-1" role="basic" aria-hidden="true">
								<div class="modal-dialog">
										<div class="modal-content">
												<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
														<h4 class="modal-title"></h4>
												</div>
												<div class="modal-body">
														<div class="form-group">
																<label><h4 class="modal-title" id="noConfirmContent">包含非未审核状态委托信息，请重新选择!</h4></label>
																
														</div>
												</div>
												<div class="modal-footer">
														<button type="button" class="btn btn-primary" data-dismiss="modal">返回</button>
												</div>
										</div>
										<!-- /.modal-content --> 
								</div>
								<!-- /.modal-dialog --> 
						</div>
			 <div class="modal fade" id="noPass" tabindex="-1" role="basic" aria-hidden="true">
								<div class="modal-dialog">
										<div class="modal-content">
												<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
														<h4 class="modal-title"></h4>
												</div>
												<div class="modal-body">
														<div class="form-group">
																<label><h4 class="modal-title" id="noPassContent">包含非未确认反馈委托信息，请重新选择!</h4></label>
																
														</div>
												</div>
												<div class="modal-footer">
														<button type="button" class="btn btn-primary" data-dismiss="modal">返回</button>
												</div>
										</div>
										<!-- /.modal-content --> 
								</div>
								<!-- /.modal-dialog --> 
						</div>
</div>

<!-- 未选中提示  -->
<div class="modal fade" id="noSelected" tabindex="-1" role="basic" aria-hidden="true">
		<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title"></h4>
						</div>
						<div class="modal-body">
								<div class="portlet-body form">
										<form action="#" class="form-horizontal">
										<input type="hidden" id="CSRFToken" name="CSRFToken" value="${csrf}" />
												<div class="form-body">
														<div class="form-group" >
																<input type="hidden" id="accountId" value="" />
																<label class="control-label">
																	<h4 class="modal-title">未选中</h4>
																</label>
														</div>
												</div>
										</form>
								</div>
						</div>
						<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
						</div>
				</div>
		</div>
</div>

</body>
<%@ include file="entrust_check_js.jsp"%>
</html>