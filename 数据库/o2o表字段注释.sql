/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2015/9/11 9:15:11                            */
/*==============================================================*/


comment on table "acc_role" is
'系统用户角色表';

comment on column "acc_role"."acc_id" is
'用户id';

comment on column "acc_role"."role_id" is
'角色id';

comment on column "acc_role"."rev1" is
'预留1';

comment on column "acc_role"."rev2" is
'预留2';

comment on column "acc_role"."rev3" is
'预留3';

comment on column "acc_role"."rev4" is
'预留4';

comment on column "acc_role"."rev5" is
'预留5';

comment on column "acc_role"."rev6" is
'预留6';

comment on column "acc_role"."rev7" is
'预留7';

comment on table "account" is
'系统用户表';

comment on column "account"."id" is
'主键';

comment on column "account"."accountName" is
'登录名称';

comment on column "account"."password" is
'密码';

comment on column "account"."trueName" is
'用户姓名';

comment on column "account"."phoneNumber" is
'联系电话';

comment on column "account"."unitCode" is
'单位代码';

comment on column "account"."state" is
'是否开启';

comment on column "account"."policeNumber" is
'警员编号';

comment on column "account"."duty" is
'职务';

comment on column "account"."createTime" is
'创建时间';

comment on column "account"."description" is
'备注';

comment on column "account"."updateTime" is
'修改时间';

comment on column "account"."rev3" is
'预留3';

comment on column "account"."rev2" is
'预留2';

comment on column "account"."rev1" is
'预留1';

comment on column "account"."rev4" is
'预留4';

comment on column "account"."rev5" is
'预留5';

comment on column "account"."rev6" is
'预留6';

comment on column "account"."rev7" is
'预留7';

comment on table "ali_account_detail" is
'阿里账户明细';

comment on column "ali_account_detail"."id" is
'主键';

comment on column "ali_account_detail"."entrust_no" is
'委托编号';

comment on column "ali_account_detail"."trans_out_order_no" is
'流水号（包含提现流水号）';

comment on column "ali_account_detail"."trans_account" is
'交易账户';

comment on column "ali_account_detail"."other_account" is
'对方帐户';

comment on column "ali_account_detail"."trans_date" is
'交易发生日期';

comment on column "ali_account_detail"."trans_dt" is
'交易发生时间';

comment on column "ali_account_detail"."trans_amount_d" is
'收入金额(+)';

comment on column "ali_account_detail"."trans_amount_c" is
'支出金额(-)';

comment on column "ali_account_detail"."balance" is
'余额';

comment on column "ali_account_detail"."sub_trans_code_desc" is
'业务类型';

comment on column "ali_account_detail"."trade_area" is
'交易发生地';

comment on column "ali_account_detail"."show_name" is
'银行名称';

comment on column "ali_account_detail"."trans_memo" is
'备注';

comment on column "ali_account_detail"."create_user" is
'创建人';

comment on column "ali_account_detail"."create_date" is
'创建时间';

comment on table "ali_login_history" is
'阿里登录历史记录';

comment on column "ali_login_history"."id" is
'主键';

comment on column "ali_login_history"."entrust_no" is
'委托编号';

comment on column "ali_login_history"."login_id" is
'登录名';

comment on column "ali_login_history"."user_id" is
'卡ID';

comment on column "ali_login_history"."name" is
'用户名字';

comment on column "ali_login_history"."client_ip" is
'用户IP';

comment on column "ali_login_history"."action_dt" is
'时间';

comment on column "ali_login_history"."create_user" is
'创建人';

comment on column "ali_login_history"."create_date" is
'创建时间';

comment on table "ali_register_info" is
'阿里反馈注册信息';

comment on column "ali_register_info"."id" is
'主键';

comment on column "ali_register_info"."entrust_no" is
'委托编号';

comment on column "ali_register_info"."iw_user_id" is
'卡ID';

comment on column "ali_register_info"."real_name" is
'真实姓名';

comment on column "ali_register_info"."gender" is
'性别';

comment on column "ali_register_info"."cert_no" is
'证件号码';

comment on column "ali_register_info"."cert_type" is
'证件类型';

comment on column "ali_register_info"."firm_name" is
'公司名称';

comment on column "ali_register_info"."login_email" is
'卡别名（邮箱）';

comment on column "ali_register_info"."login_mobile" is
'卡别名（手机）';

comment on column "ali_register_info"."inst_id" is
'银行名称';

comment on column "ali_register_info"."inst_account_no" is
'银行卡号';

comment on column "ali_register_info"."balance" is
'余额';

comment on column "ali_register_info"."create_user" is
'创建人';

comment on column "ali_register_info"."create_date" is
'创建时间';

comment on column "ali_register_info"."nick" is
'会员名';

comment on column "ali_register_info"."mobile" is
'绑定手机';

comment on column "ali_register_info"."alipay_id" is
'支付宝id';

comment on column "ali_register_info"."register_time" is
'注册时间yyyymmddhh24miss';

comment on column "ali_register_info"."bind_email" is
'绑定邮箱';

comment on column "ali_register_info"."last_login" is
'最后登录时间yyyymmddhh24miss';

comment on table "ali_transaction_records" is
'阿里交易记录';

comment on column "ali_transaction_records"."id" is
'主键';

comment on column "ali_transaction_records"."entrust_no" is
'委托编号';

comment on column "ali_transaction_records"."trade_no" is
'交易号';

comment on column "ali_transaction_records"."out_trade_no" is
'外部交易号';

comment on column "ali_transaction_records"."trade_status_desc" is
'交易状态';

comment on column "ali_transaction_records"."partner_id" is
'合作ID';

comment on column "ali_transaction_records"."trade_buyer_id" is
'买家卡ID';

comment on column "ali_transaction_records"."buyer_info" is
'买家信息';

comment on column "ali_transaction_records"."trade_seller_id" is
'卖家卡ID';

comment on column "ali_transaction_records"."seller_info" is
'卖家信息';

comment on column "ali_transaction_records"."trade_total_amt" is
'交易金额';

comment on column "ali_transaction_records"."trade_receive_pay_date" is
'收款时间';

comment on column "ali_transaction_records"."gmt_modified" is
'最后修改时间';

comment on column "ali_transaction_records"."trade_create_date" is
'创建时间';

comment on column "ali_transaction_records"."trade_type_desc" is
'交易类型';

comment on column "ali_transaction_records"."trade_from_desc" is
'来源地';

comment on column "ali_transaction_records"."goods_title" is
'商品名称';

comment on column "ali_transaction_records"."receive_address" is
'收货人地址';

comment on column "ali_transaction_records"."data_create_user" is
'数据创建人';

comment on column "ali_transaction_records"."data_create_date" is
'数据入库时间';

comment on table "ali_transfer_accounts_detail" is
'阿里转账明细';

comment on column "ali_transfer_accounts_detail"."id" is
'主键';

comment on column "ali_transfer_accounts_detail"."entrust_no" is
'委托编号';

comment on column "ali_transfer_accounts_detail"."transfer_no" is
'流水号';

comment on column "ali_transfer_accounts_detail"."sender_userid" is
'付款方';

comment on column "ali_transfer_accounts_detail"."receiver_userid" is
'收款方';

comment on column "ali_transfer_accounts_detail"."receiver_inst" is
'收款机构';

comment on column "ali_transfer_accounts_detail"."trans_succ_date" is
'到账时间';

comment on column "ali_transfer_accounts_detail"."trans_amount" is
'转账金额';

comment on column "ali_transfer_accounts_detail"."product_name" is
'业务类型';

comment on column "ali_transfer_accounts_detail"."trade_area" is
'交易发生地';

comment on column "ali_transfer_accounts_detail"."withdraw_no" is
'提现流水号';

comment on column "ali_transfer_accounts_detail"."create_user" is
'创建人';

comment on column "ali_transfer_accounts_detail"."create_date" is
'创建时间';

comment on table "dic" is
'系统字典表';

comment on column "dic"."id" is
'主键';

comment on column "dic"."dicTypeId" is
'字典类型';

comment on column "dic"."dicKey" is
'字典码';

comment on column "dic"."dicName" is
'字典值';

comment on column "dic"."dicParentKey" is
'父级字典码';

comment on column "dic"."description" is
'备注';

comment on column "dic"."dicLevel" is
'字典级别';

comment on column "dic"."status" is
'是否启用';

comment on column "dic"."datareource" is
'数据来源';

comment on column "dic"."rev1" is
'预留1';

comment on column "dic"."rev2" is
'预留2';

comment on column "dic"."rev3" is
'预留3';

comment on column "dic"."rev4" is
'预留4';

comment on column "dic"."rev5" is
'预留5';

comment on column "dic"."rev6" is
'预留6';

comment on column "dic"."rev7" is
'预留7';

comment on table "dic_type" is
'系统字典类型表';

comment on column "dic_type"."id" is
'主键';

comment on column "dic_type"."dicTypeKey" is
'字典类型key';

comment on column "dic_type"."dicTypeName" is
'字典名称';

comment on column "dic_type"."description" is
'备注';

comment on column "dic_type"."rev1" is
'预留1';

comment on column "dic_type"."rev2" is
'预留2';

comment on column "dic_type"."rev3" is
'预留3';

comment on column "dic_type"."rev4" is
'预留4';

comment on column "dic_type"."rev5" is
'预留5';

comment on column "dic_type"."rev6" is
'预留6';

comment on column "dic_type"."rev7" is
'预留7';

comment on table "entrus_attachment" is
'委托附件文书表';

comment on column "entrus_attachment"."id" is
'主键';

comment on column "entrus_attachment"."file_name" is
'附件名称';

comment on column "entrus_attachment"."upload_time" is
'附件上传时间';

comment on column "entrus_attachment"."upload_user" is
'附件上传人';

comment on column "entrus_attachment"."update_user" is
'附件修改人';

comment on column "entrus_attachment"."update_date" is
'附件修改时间';

comment on column "entrus_attachment"."file_content" is
'附件内容';

comment on column "entrus_attachment"."entrust_no" is
'委托编号';

comment on column "entrus_attachment"."delete_flag" is
'删除标识';

comment on column "entrus_attachment"."rev1" is
'预留1';

comment on column "entrus_attachment"."rev2" is
'预留2';

comment on column "entrus_attachment"."rev3" is
'预留3';

comment on column "entrus_attachment"."rev4" is
'预留4';

comment on column "entrus_attachment"."rev5" is
'预留5';

comment on column "entrus_attachment"."rev6" is
'预留6';

comment on column "entrus_attachment"."rev7" is
'预留7';

comment on table "entrus_investigation_info" is
'委托协查信息表';

comment on column "entrus_investigation_info"."id" is
'主键';

comment on column "entrus_investigation_info"."query_type" is
'查询类型';

comment on column "entrus_investigation_info"."entrust_no" is
'委托编号';

comment on column "entrus_investigation_info"."create_user" is
'创建人';

comment on column "entrus_investigation_info"."create_date" is
'创建时间';

comment on column "entrus_investigation_info"."update_user" is
'修改人';

comment on column "entrus_investigation_info"."update_date" is
'修改时间';

comment on column "entrus_investigation_info"."delete_flag" is
'删除标识';

comment on column "entrus_investigation_info"."rev1" is
'预留1';

comment on column "entrus_investigation_info"."rev2" is
'预留2';

comment on column "entrus_investigation_info"."rev3" is
'预留3';

comment on column "entrus_investigation_info"."rev4" is
'预留4';

comment on column "entrus_investigation_info"."rev5" is
'预留5';

comment on column "entrus_investigation_info"."rev6" is
'预留6';

comment on column "entrus_investigation_info"."rev7" is
'预留7';

comment on table "entrus_investigation_typeinfo" is
'委托协查信息分类表';

comment on column "entrus_investigation_typeinfo"."id" is
'id';

comment on column "entrus_investigation_typeinfo"."entrust_no" is
'委托编号';

comment on column "entrus_investigation_typeinfo"."invest_type" is
'协助项目编号';

comment on column "entrus_investigation_typeinfo"."invest_content" is
'协助项目内容';

comment on column "entrus_investigation_typeinfo"."invest_desc" is
'协助项目说明';

comment on column "entrus_investigation_typeinfo"."create_user" is
'创建人';

comment on column "entrus_investigation_typeinfo"."create_date" is
'创建时间';

comment on column "entrus_investigation_typeinfo"."update_user" is
'修改人';

comment on column "entrus_investigation_typeinfo"."update_date" is
'修改时间';

comment on column "entrus_investigation_typeinfo"."delete_flag" is
'删除标识';

comment on column "entrus_investigation_typeinfo"."rev1" is
'预留1';

comment on column "entrus_investigation_typeinfo"."rev2" is
'预留2';

comment on column "entrus_investigation_typeinfo"."rev3" is
'预留3';

comment on column "entrus_investigation_typeinfo"."rev4" is
'预留4';

comment on column "entrus_investigation_typeinfo"."rev5" is
'预留5';

comment on column "entrus_investigation_typeinfo"."rev6" is
'预留6';

comment on column "entrus_investigation_typeinfo"."rev7" is
'预留7';

comment on table "entrust_base_info" is
'委托信息表';

comment on column "entrust_base_info"."id" is
'id';

comment on column "entrust_base_info"."entrust_no" is
'委托编号';

comment on column "entrust_base_info"."entrust_ser" is
'委托序号';

comment on column "entrust_base_info"."entrust_number" is
'委托次数';

comment on column "entrust_base_info"."entrust_type" is
'委托类型';

comment on column "entrust_base_info"."province_code" is
'办案单位省code';

comment on column "entrust_base_info"."city_code" is
'办案单位市code';

comment on column "entrust_base_info"."unit" is
'办案单位';

comment on column "entrust_base_info"."person_name" is
'办案人';

comment on column "entrust_base_info"."telephone" is
'办案人联系电话';

comment on column "entrust_base_info"."title" is
'办案人职务';

comment on column "entrust_base_info"."istake" is
'是否来人调取';

comment on column "entrust_base_info"."visitor_name" is
'来访人姓名';

comment on column "entrust_base_info"."visitor_telephone" is
'来访人联系方式';

comment on column "entrust_base_info"."visitor_unit" is
'来访人单位';

comment on column "entrust_base_info"."visitor_title" is
'来访人职务';

comment on column "entrust_base_info"."case_stage" is
'案件办理阶段';

comment on column "entrust_base_info"."is_reply" is
'是否需要回函';

comment on column "entrust_base_info"."memo" is
'备注';

comment on column "entrust_base_info"."is_share_caseinfo" is
'是否共享案件信息';

comment on column "entrust_base_info"."is_share_queryresult" is
'是否共享查询结果';

comment on column "entrust_base_info"."create_user" is
'创建人';

comment on column "entrust_base_info"."create_date" is
'创建时间';

comment on column "entrust_base_info"."update_user" is
'修改人';

comment on column "entrust_base_info"."update_date" is
'修改时间';

comment on column "entrust_base_info"."delete_flag" is
'删除标识';

comment on column "entrust_base_info"."data_resource" is
'数据来源';

comment on column "entrust_base_info"."process_state" is
'流程状态';

comment on column "entrust_base_info"."rev1" is
'预留1';

comment on column "entrust_base_info"."rev2" is
'预留2';

comment on column "entrust_base_info"."rev3" is
'预留3';

comment on column "entrust_base_info"."rev4" is
'预留4';

comment on column "entrust_base_info"."rev5" is
'预留5';

comment on column "entrust_base_info"."rev6" is
'预留6';

comment on column "entrust_base_info"."rev7" is
'预留7';

comment on table "entrust_case_info" is
'委托案件信息';

comment on column "entrust_case_info"."id" is
'id';

comment on column "entrust_case_info"."entrust_no" is
'委托编号';

comment on column "entrust_case_info"."case_name" is
'案件名称';

comment on column "entrust_case_info"."case_occur_date" is
'案发时间';

comment on column "entrust_case_info"."case_no" is
'案件编号';

comment on column "entrust_case_info"."case_money_amout" is
'案件金额';

comment on column "entrust_case_info"."brand" is
'涉案品牌';

comment on column "entrust_case_info"."case_type" is
'案件类型';

comment on column "entrust_case_info"."case_info" is
'简要案情';

comment on column "entrust_case_info"."create_user" is
'创建人';

comment on column "entrust_case_info"."create_date" is
'创建时间';

comment on column "entrust_case_info"."update_user" is
'修改人';

comment on column "entrust_case_info"."update_date" is
'修改时间';

comment on column "entrust_case_info"."delete_flag" is
'删除标识';

comment on column "entrust_case_info"."is_end" is
'是否结案';

comment on column entrust_case_info.is_series is 
'是否串并1(串并)0(未串并)';

comment on column entrust_case_info.series_user is 
'串并人';

comment on column entrust_case_info.series_date is 
'串并时间';

comment on column "entrust_case_info"."rev1" is
'预留1';

comment on column "entrust_case_info"."rev2" is
'预留2';

comment on column "entrust_case_info"."rev3" is
'预留3';

comment on column "entrust_case_info"."rev4" is
'预留4';

comment on column "entrust_case_info"."rev5" is
'预留5';

comment on column "entrust_case_info"."rev6" is
'预留6';

comment on column "entrust_case_info"."rev7" is
'预留7';

comment on table "entrust_check_list" is
'委托审核表';

comment on column "entrust_check_list"."id" is
'主键';

comment on column "entrust_check_list"."entrust_no" is
'委托编号';

comment on column "entrust_check_list"."check_key" is
'审核状态编码';

comment on column "entrust_check_list"."check_user" is
'审核人';

comment on column "entrust_check_list"."check_date" is
'审核时间';

comment on column "entrust_check_list"."check_ispass" is
'审核是否通过';

comment on column "entrust_check_list"."check_desdc" is
'审核说明';

comment on column "entrust_check_list"."is_exec" is
'是否执行';

comment on column "entrust_check_list"."exec_no" is
'执行序号';

comment on column "entrust_check_list"."rev1" is
'预留1';

comment on column "entrust_check_list"."rev2" is
'预留2';

comment on column "entrust_check_list"."rev3" is
'预留3';

comment on column "entrust_check_list"."rev4" is
'预留4';

comment on column "entrust_check_list"."rev5" is
'预留5';

comment on column "entrust_check_list"."rev6" is
'预留6';

comment on column "entrust_check_list"."rev7" is
'预留7';

comment on table "entrust_satisfactory_results" is
'委托满意结果调查表';

comment on column "entrust_satisfactory_results"."id" is
'主键';

comment on column "entrust_satisfactory_results"."entrust_no" is
'委托编号';

comment on column "entrust_satisfactory_results"."satisfactory_key" is
'反馈项目key';

comment on column "entrust_satisfactory_results"."satisfactory_result" is
'反馈项结果';

comment on column "entrust_satisfactory_results"."type" is
'项目类型';

comment on column "entrust_satisfactory_results"."advice" is
'意见和建议';

comment on column "entrust_satisfactory_results"."create_user" is
'创建人';

comment on column "entrust_satisfactory_results"."create_date" is
'创建时间';

comment on column "entrust_satisfactory_results"."update_user" is
'修改人';

comment on column "entrust_satisfactory_results"."update_date" is
'修改时间';

comment on column "entrust_satisfactory_results"."delete_flag" is
'删除标识';

comment on column "entrust_satisfactory_results"."rev1" is
'预留1';

comment on column "entrust_satisfactory_results"."rev2" is
'预留2';

comment on column "entrust_satisfactory_results"."rev3" is
'预留3';

comment on column "entrust_satisfactory_results"."rev4" is
'预留4';

comment on column "entrust_satisfactory_results"."rev5" is
'预留5';

comment on column "entrust_satisfactory_results"."rev6" is
'预留6';

comment on column "entrust_satisfactory_results"."rev7" is
'预留7';

comment on table "entrust_suspect_info" is
'案件嫌疑人信息表';

comment on column "entrust_suspect_info"."id" is
'id';

comment on column "entrust_suspect_info"."entrust_no" is
'委托编号';

comment on column "entrust_suspect_info"."suspect_name" is
'嫌疑人姓名';

comment on column "entrust_suspect_info"."suspect_card_no" is
'嫌疑人身份证';

comment on column "entrust_suspect_info"."suspect_telephone" is
'嫌疑人手机';

comment on column "entrust_suspect_info"."suspect_taobao_no" is
'嫌疑人淘宝';

comment on column "entrust_suspect_info"."suspect_alipay" is
'嫌疑人支付宝';

comment on column "entrust_suspect_info"."suspect_qq" is
'嫌疑人qq';

comment on column "entrust_suspect_info"."suspect_email" is
'嫌疑人邮箱';

comment on column "entrust_suspect_info"."create_user" is
'创建人';

comment on column "entrust_suspect_info"."create_date" is
'创建时间';

comment on column "entrust_suspect_info"."update_user" is
'修改人';

comment on column "entrust_suspect_info"."update_date" is
'修改时间';

comment on column "entrust_suspect_info"."delete_flag" is
'删除标识';

comment on column "entrust_suspect_info"."rev1" is
'预留1';

comment on column "entrust_suspect_info"."rev2" is
'预留2';

comment on column "entrust_suspect_info"."rev3" is
'预留3';

comment on column "entrust_suspect_info"."rev4" is
'预留4';

comment on column "entrust_suspect_info"."rev5" is
'预留5';

comment on column "entrust_suspect_info"."rev6" is
'预留6';

comment on column "entrust_suspect_info"."rev7" is
'预留7';

comment on table "entrust_victim_info" is
'案件受害人信息表';

comment on column "entrust_victim_info"."id" is
'主键';

comment on column "entrust_victim_info"."entrust_no" is
'委托编号';

comment on column "entrust_victim_info"."victim_name" is
'受害人姓名';

comment on column "entrust_victim_info"."victim_card_no" is
'受害人身份证';

comment on column "entrust_victim_info"."victim_telephone" is
'受害人手机';

comment on column "entrust_victim_info"."victim_taobao_no" is
'受害人淘宝账号';

comment on column "entrust_victim_info"."victim_alipay" is
'受害人支付宝账号';

comment on column "entrust_victim_info"."victim_address" is
'受害人地址';

comment on column "entrust_victim_info"."create_user" is
'创建人';

comment on column "entrust_victim_info"."create_date" is
'创建时间';

comment on column "entrust_victim_info"."update_user" is
'修改人';

comment on column "entrust_victim_info"."update_date" is
'修改时间';

comment on column "entrust_victim_info"."delete_flag" is
'删除标识';

comment on column "entrust_victim_info"."rev1" is
'预留1';

comment on column "entrust_victim_info"."rev2" is
'预留2';

comment on column "entrust_victim_info"."rev3" is
'预留3';

comment on column "entrust_victim_info"."rev4" is
'预留4';

comment on column "entrust_victim_info"."rev5" is
'预留5';

comment on column "entrust_victim_info"."rev6" is
'预留6';

comment on column "entrust_victim_info"."rev7" is
'预留7';

comment on table "log" is
'系统日志表';

comment on column "log"."id" is
'主键';

comment on column "log"."userId" is
'访问者id';

comment on column "log"."username" is
'访问者姓名';

comment on column "log"."action" is
'操作动作';

comment on column "log"."module" is
'模块编码';

comment on column "log"."actionTime" is
'操作次数';

comment on column "log"."userIP" is
'操作者ip';

comment on column "log"."operTime" is
'操作时间';

comment on column "log"."actiondesc" is
'操作详情';

comment on column "log"."rev1" is
'预留1';

comment on column "log"."rev2" is
'预留2';

comment on column "log"."rev3" is
'预留3';

comment on column "log"."rev4" is
'预留4';

comment on column "log"."rev5" is
'预留5';

comment on column "log"."rev6" is
'预留6';

comment on column "log"."rev7" is
'预留7';

comment on table "res_roles" is
'系统角色资源表';

comment on column "res_roles"."role_id" is
'角色id';

comment on column "res_roles"."resc_id" is
'资源id';

comment on column "res_roles"."rev1" is
'预留1';

comment on column "res_roles"."rev2" is
'预留2';

comment on column "res_roles"."rev3" is
'预留3';

comment on column "res_roles"."rev4" is
'预留4';

comment on column "res_roles"."rev5" is
'预留5';

comment on column "res_roles"."rev6" is
'预留6';

comment on column "res_roles"."rev7" is
'预留7';

comment on table "resources" is
'系统资源表';

comment on column "resources"."id" is
'主键';

comment on column "resources"."name" is
'资源名称';

comment on column "resources"."parentId" is
'上级id';

comment on column "resources"."resKey" is
'资源标识';

comment on column "resources"."type" is
'类型';

comment on column "resources"."resUrl" is
'资源地址';

comment on column "resources"."level" is
'级别';

comment on column "resources"."description" is
'备注';

comment on column "resources"."rev1" is
'预留1';

comment on column "resources"."rev2" is
'预留2';

comment on column "resources"."rev3" is
'预留3';

comment on column "resources"."rev4" is
'预留4';

comment on column "resources"."rev5" is
'预留5';

comment on column "resources"."rev6" is
'预留6';

comment on column "resources"."rev7" is
'预留7';

comment on table "role" is
'系统角色表';

comment on column "role"."id" is
'主键';

comment on column "role"."name" is
'角色名称';

comment on column "role"."description" is
'角色说明';

comment on column "role"."enable" is
'是否可用';

comment on column "role"."roleKey" is
'角色标志';

comment on column "role"."rev1" is
'预留1';

comment on column "role"."rev2" is
'预留2';

comment on column "role"."rev3" is
'预留3';

comment on column "role"."rev4" is
'预留4';

comment on column "role"."rev5" is
'预留5';

comment on column "role"."rev6" is
'预留6';

comment on column "role"."rev7" is
'预留7';

comment on table "series_case_alipay" is
'串并案依据支付宝';

comment on column "series_case_alipay"."id" is
'主键';

comment on column "series_case_alipay"."entrust_case_id" is
'案件id';

comment on column "series_case_alipay"."alipay_card" is
'支付宝账号';

comment on column "series_case_alipay"."create_user" is
'创建人';

comment on column "series_case_alipay"."create_date" is
'创建时间';

comment on column "series_case_alipay"."update_user" is
'修改人';

comment on column "series_case_alipay"."update_date" is
'修改时间';

comment on column "series_case_alipay"."rev1" is
'预留1';

comment on column "series_case_alipay"."rev2" is
'预留2';

comment on column "series_case_alipay"."rev3" is
'预留3';

comment on column "series_case_alipay"."rev4" is
'预留4';

comment on column "series_case_alipay"."rev5" is
'预留5';

comment on column "series_case_alipay"."rev6" is
'预留6';

comment on column "series_case_alipay"."rev7" is
'预留7';

comment on table "series_case_bankcard" is
'串并案依据银行卡号';

comment on column "series_case_bankcard"."id" is
'主键';

comment on column "series_case_bankcard"."entrust_case_id" is
'案件id';

comment on column "series_case_bankcard"."bankcard" is
'银行卡号';

comment on column "series_case_bankcard"."create_user" is
'创建人';

comment on column "series_case_bankcard"."create_date" is
'创建时间';

comment on column "series_case_bankcard"."update_user" is
'修改人';

comment on column "series_case_bankcard"."update_date" is
'修改时间';

comment on column "series_case_bankcard"."rev1" is
'预留1';

comment on column "series_case_bankcard"."rev2" is
'预留2';

comment on column "series_case_bankcard"."rev3" is
'预留3';

comment on column "series_case_bankcard"."rev4" is
'预留4';

comment on column "series_case_bankcard"."rev5" is
'预留5';

comment on column "series_case_bankcard"."rev6" is
'预留6';

comment on column "series_case_bankcard"."rev7" is
'预留7';

comment on table "series_case_certificate" is
'串并案依据证件号';

comment on column "series_case_certificate"."id" is
'主键';

comment on column "series_case_certificate"."entrust_case_id" is
'案件id';

comment on column "series_case_certificate"."certificate_no" is
'证件号';

comment on column "series_case_certificate"."create_user" is
'创建人';

comment on column "series_case_certificate"."create_date" is
'创建时间';

comment on column "series_case_certificate"."update_user" is
'修改人';

comment on column "series_case_certificate"."update_date" is
'修改时间';

comment on column "series_case_certificate"."rev1" is
'预留1';

comment on column "series_case_certificate"."rev2" is
'预留2';

comment on column "series_case_certificate"."rev3" is
'预留3';

comment on column "series_case_certificate"."rev4" is
'预留4';

comment on column "series_case_certificate"."rev5" is
'预留5';

comment on column "series_case_certificate"."rev6" is
'预留6';

comment on column "series_case_certificate"."rev7" is
'预留7';

comment on table "series_case_detail" is
'串并案详情表';

comment on column "series_case_detail"."id" is
'主键';

comment on column "series_case_detail"."series_case_id" is
'串并案id';

comment on column "series_case_detail"."case_id" is
'案件编号';

comment on column "series_case_detail"."entrust_no" is
'委托编号';

comment on column "series_case_detail"."series_person" is
'串并依据';

comment on column "series_case_detail"."create_user" is
'创建人';

comment on column "series_case_detail"."create_date" is
'创建时间';

comment on column "series_case_detail"."update_user" is
'修改人';

comment on column "series_case_detail"."update_date" is
'修改时间';

comment on column "series_case_detail"."delete_flag" is
'删除标识';

comment on column "series_case_detail"."rev1" is
'预留1';

comment on column "series_case_detail"."rev2" is
'预留2';

comment on column "series_case_detail"."rev3" is
'预留3';

comment on column "series_case_detail"."rev4" is
'预留4';

comment on column "series_case_detail"."rev5" is
'预留5';

comment on column "series_case_detail"."rev6" is
'预留6';

comment on column "series_case_detail"."rev7" is
'预留7';

comment on table "series_case_info" is
'串并案信息表';

comment on column "series_case_info"."id" is
'主键';

comment on column "series_case_info"."series_case_no" is
'串并案编号';

comment on column "series_case_info"."series_case_name" is
'串并案名称';

comment on column "series_case_info"."series_reason" is
'串并依据';

comment on column "series_case_info"."series_desc" is
'串并案描述';

comment on column "series_case_info"."create_user" is
'创建人';

comment on column "series_case_info"."create_date" is
'创建时间';

comment on column "series_case_info"."update_user" is
'修改人';

comment on column "series_case_info"."update_date" is
'修改时间';

comment on column "series_case_info"."delete_flag" is
'删除标识';

comment on column "series_case_info"."rev1" is
'预留1';

comment on column "series_case_info"."rev2" is
'预留2';

comment on column "series_case_info"."rev3" is
'预留3';

comment on column "series_case_info"."rev4" is
'预留4';

comment on column "series_case_info"."rev5" is
'预留5';

comment on column "series_case_info"."rev6" is
'预留6';

comment on column "series_case_info"."rev7" is
'预留7';

comment on table "series_case_telephone" is
'串并案依据电话号码';

comment on column "series_case_telephone"."id" is
'主键';

comment on column "series_case_telephone"."entrust_case_id" is
'案件id';

comment on column "series_case_telephone"."telephone" is
'手机号码';

comment on column "series_case_telephone"."create_user" is
'创建人';

comment on column "series_case_telephone"."create_date" is
'创建时间';

comment on column "series_case_telephone"."update_user" is
'修改人';

comment on column "series_case_telephone"."update_date" is
'修改时间';

comment on column "series_case_telephone"."rev1" is
'预留1';

comment on column "series_case_telephone"."rev2" is
'预留2';

comment on column "series_case_telephone"."rev3" is
'预留3';

comment on column "series_case_telephone"."rev4" is
'预留4';

comment on column "series_case_telephone"."rev5" is
'预留5';

comment on column "series_case_telephone"."rev6" is
'预留6';

comment on column "series_case_telephone"."rev7" is
'预留7';

