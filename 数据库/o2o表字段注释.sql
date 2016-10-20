/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2015/9/11 9:15:11                            */
/*==============================================================*/


comment on table "acc_role" is
'ϵͳ�û���ɫ��';

comment on column "acc_role"."acc_id" is
'�û�id';

comment on column "acc_role"."role_id" is
'��ɫid';

comment on column "acc_role"."rev1" is
'Ԥ��1';

comment on column "acc_role"."rev2" is
'Ԥ��2';

comment on column "acc_role"."rev3" is
'Ԥ��3';

comment on column "acc_role"."rev4" is
'Ԥ��4';

comment on column "acc_role"."rev5" is
'Ԥ��5';

comment on column "acc_role"."rev6" is
'Ԥ��6';

comment on column "acc_role"."rev7" is
'Ԥ��7';

comment on table "account" is
'ϵͳ�û���';

comment on column "account"."id" is
'����';

comment on column "account"."accountName" is
'��¼����';

comment on column "account"."password" is
'����';

comment on column "account"."trueName" is
'�û�����';

comment on column "account"."phoneNumber" is
'��ϵ�绰';

comment on column "account"."unitCode" is
'��λ����';

comment on column "account"."state" is
'�Ƿ���';

comment on column "account"."policeNumber" is
'��Ա���';

comment on column "account"."duty" is
'ְ��';

comment on column "account"."createTime" is
'����ʱ��';

comment on column "account"."description" is
'��ע';

comment on column "account"."updateTime" is
'�޸�ʱ��';

comment on column "account"."rev3" is
'Ԥ��3';

comment on column "account"."rev2" is
'Ԥ��2';

comment on column "account"."rev1" is
'Ԥ��1';

comment on column "account"."rev4" is
'Ԥ��4';

comment on column "account"."rev5" is
'Ԥ��5';

comment on column "account"."rev6" is
'Ԥ��6';

comment on column "account"."rev7" is
'Ԥ��7';

comment on table "ali_account_detail" is
'�����˻���ϸ';

comment on column "ali_account_detail"."id" is
'����';

comment on column "ali_account_detail"."entrust_no" is
'ί�б��';

comment on column "ali_account_detail"."trans_out_order_no" is
'��ˮ�ţ�����������ˮ�ţ�';

comment on column "ali_account_detail"."trans_account" is
'�����˻�';

comment on column "ali_account_detail"."other_account" is
'�Է��ʻ�';

comment on column "ali_account_detail"."trans_date" is
'���׷�������';

comment on column "ali_account_detail"."trans_dt" is
'���׷���ʱ��';

comment on column "ali_account_detail"."trans_amount_d" is
'������(+)';

comment on column "ali_account_detail"."trans_amount_c" is
'֧�����(-)';

comment on column "ali_account_detail"."balance" is
'���';

comment on column "ali_account_detail"."sub_trans_code_desc" is
'ҵ������';

comment on column "ali_account_detail"."trade_area" is
'���׷�����';

comment on column "ali_account_detail"."show_name" is
'��������';

comment on column "ali_account_detail"."trans_memo" is
'��ע';

comment on column "ali_account_detail"."create_user" is
'������';

comment on column "ali_account_detail"."create_date" is
'����ʱ��';

comment on table "ali_login_history" is
'�����¼��ʷ��¼';

comment on column "ali_login_history"."id" is
'����';

comment on column "ali_login_history"."entrust_no" is
'ί�б��';

comment on column "ali_login_history"."login_id" is
'��¼��';

comment on column "ali_login_history"."user_id" is
'��ID';

comment on column "ali_login_history"."name" is
'�û�����';

comment on column "ali_login_history"."client_ip" is
'�û�IP';

comment on column "ali_login_history"."action_dt" is
'ʱ��';

comment on column "ali_login_history"."create_user" is
'������';

comment on column "ali_login_history"."create_date" is
'����ʱ��';

comment on table "ali_register_info" is
'���ﷴ��ע����Ϣ';

comment on column "ali_register_info"."id" is
'����';

comment on column "ali_register_info"."entrust_no" is
'ί�б��';

comment on column "ali_register_info"."iw_user_id" is
'��ID';

comment on column "ali_register_info"."real_name" is
'��ʵ����';

comment on column "ali_register_info"."gender" is
'�Ա�';

comment on column "ali_register_info"."cert_no" is
'֤������';

comment on column "ali_register_info"."cert_type" is
'֤������';

comment on column "ali_register_info"."firm_name" is
'��˾����';

comment on column "ali_register_info"."login_email" is
'�����������䣩';

comment on column "ali_register_info"."login_mobile" is
'���������ֻ���';

comment on column "ali_register_info"."inst_id" is
'��������';

comment on column "ali_register_info"."inst_account_no" is
'���п���';

comment on column "ali_register_info"."balance" is
'���';

comment on column "ali_register_info"."create_user" is
'������';

comment on column "ali_register_info"."create_date" is
'����ʱ��';

comment on column "ali_register_info"."nick" is
'��Ա��';

comment on column "ali_register_info"."mobile" is
'���ֻ�';

comment on column "ali_register_info"."alipay_id" is
'֧����id';

comment on column "ali_register_info"."register_time" is
'ע��ʱ��yyyymmddhh24miss';

comment on column "ali_register_info"."bind_email" is
'������';

comment on column "ali_register_info"."last_login" is
'����¼ʱ��yyyymmddhh24miss';

comment on table "ali_transaction_records" is
'���ｻ�׼�¼';

comment on column "ali_transaction_records"."id" is
'����';

comment on column "ali_transaction_records"."entrust_no" is
'ί�б��';

comment on column "ali_transaction_records"."trade_no" is
'���׺�';

comment on column "ali_transaction_records"."out_trade_no" is
'�ⲿ���׺�';

comment on column "ali_transaction_records"."trade_status_desc" is
'����״̬';

comment on column "ali_transaction_records"."partner_id" is
'����ID';

comment on column "ali_transaction_records"."trade_buyer_id" is
'��ҿ�ID';

comment on column "ali_transaction_records"."buyer_info" is
'�����Ϣ';

comment on column "ali_transaction_records"."trade_seller_id" is
'���ҿ�ID';

comment on column "ali_transaction_records"."seller_info" is
'������Ϣ';

comment on column "ali_transaction_records"."trade_total_amt" is
'���׽��';

comment on column "ali_transaction_records"."trade_receive_pay_date" is
'�տ�ʱ��';

comment on column "ali_transaction_records"."gmt_modified" is
'����޸�ʱ��';

comment on column "ali_transaction_records"."trade_create_date" is
'����ʱ��';

comment on column "ali_transaction_records"."trade_type_desc" is
'��������';

comment on column "ali_transaction_records"."trade_from_desc" is
'��Դ��';

comment on column "ali_transaction_records"."goods_title" is
'��Ʒ����';

comment on column "ali_transaction_records"."receive_address" is
'�ջ��˵�ַ';

comment on column "ali_transaction_records"."data_create_user" is
'���ݴ�����';

comment on column "ali_transaction_records"."data_create_date" is
'�������ʱ��';

comment on table "ali_transfer_accounts_detail" is
'����ת����ϸ';

comment on column "ali_transfer_accounts_detail"."id" is
'����';

comment on column "ali_transfer_accounts_detail"."entrust_no" is
'ί�б��';

comment on column "ali_transfer_accounts_detail"."transfer_no" is
'��ˮ��';

comment on column "ali_transfer_accounts_detail"."sender_userid" is
'���';

comment on column "ali_transfer_accounts_detail"."receiver_userid" is
'�տ';

comment on column "ali_transfer_accounts_detail"."receiver_inst" is
'�տ����';

comment on column "ali_transfer_accounts_detail"."trans_succ_date" is
'����ʱ��';

comment on column "ali_transfer_accounts_detail"."trans_amount" is
'ת�˽��';

comment on column "ali_transfer_accounts_detail"."product_name" is
'ҵ������';

comment on column "ali_transfer_accounts_detail"."trade_area" is
'���׷�����';

comment on column "ali_transfer_accounts_detail"."withdraw_no" is
'������ˮ��';

comment on column "ali_transfer_accounts_detail"."create_user" is
'������';

comment on column "ali_transfer_accounts_detail"."create_date" is
'����ʱ��';

comment on table "dic" is
'ϵͳ�ֵ��';

comment on column "dic"."id" is
'����';

comment on column "dic"."dicTypeId" is
'�ֵ�����';

comment on column "dic"."dicKey" is
'�ֵ���';

comment on column "dic"."dicName" is
'�ֵ�ֵ';

comment on column "dic"."dicParentKey" is
'�����ֵ���';

comment on column "dic"."description" is
'��ע';

comment on column "dic"."dicLevel" is
'�ֵ伶��';

comment on column "dic"."status" is
'�Ƿ�����';

comment on column "dic"."datareource" is
'������Դ';

comment on column "dic"."rev1" is
'Ԥ��1';

comment on column "dic"."rev2" is
'Ԥ��2';

comment on column "dic"."rev3" is
'Ԥ��3';

comment on column "dic"."rev4" is
'Ԥ��4';

comment on column "dic"."rev5" is
'Ԥ��5';

comment on column "dic"."rev6" is
'Ԥ��6';

comment on column "dic"."rev7" is
'Ԥ��7';

comment on table "dic_type" is
'ϵͳ�ֵ����ͱ�';

comment on column "dic_type"."id" is
'����';

comment on column "dic_type"."dicTypeKey" is
'�ֵ�����key';

comment on column "dic_type"."dicTypeName" is
'�ֵ�����';

comment on column "dic_type"."description" is
'��ע';

comment on column "dic_type"."rev1" is
'Ԥ��1';

comment on column "dic_type"."rev2" is
'Ԥ��2';

comment on column "dic_type"."rev3" is
'Ԥ��3';

comment on column "dic_type"."rev4" is
'Ԥ��4';

comment on column "dic_type"."rev5" is
'Ԥ��5';

comment on column "dic_type"."rev6" is
'Ԥ��6';

comment on column "dic_type"."rev7" is
'Ԥ��7';

comment on table "entrus_attachment" is
'ί�и��������';

comment on column "entrus_attachment"."id" is
'����';

comment on column "entrus_attachment"."file_name" is
'��������';

comment on column "entrus_attachment"."upload_time" is
'�����ϴ�ʱ��';

comment on column "entrus_attachment"."upload_user" is
'�����ϴ���';

comment on column "entrus_attachment"."update_user" is
'�����޸���';

comment on column "entrus_attachment"."update_date" is
'�����޸�ʱ��';

comment on column "entrus_attachment"."file_content" is
'��������';

comment on column "entrus_attachment"."entrust_no" is
'ί�б��';

comment on column "entrus_attachment"."delete_flag" is
'ɾ����ʶ';

comment on column "entrus_attachment"."rev1" is
'Ԥ��1';

comment on column "entrus_attachment"."rev2" is
'Ԥ��2';

comment on column "entrus_attachment"."rev3" is
'Ԥ��3';

comment on column "entrus_attachment"."rev4" is
'Ԥ��4';

comment on column "entrus_attachment"."rev5" is
'Ԥ��5';

comment on column "entrus_attachment"."rev6" is
'Ԥ��6';

comment on column "entrus_attachment"."rev7" is
'Ԥ��7';

comment on table "entrus_investigation_info" is
'ί��Э����Ϣ��';

comment on column "entrus_investigation_info"."id" is
'����';

comment on column "entrus_investigation_info"."query_type" is
'��ѯ����';

comment on column "entrus_investigation_info"."entrust_no" is
'ί�б��';

comment on column "entrus_investigation_info"."create_user" is
'������';

comment on column "entrus_investigation_info"."create_date" is
'����ʱ��';

comment on column "entrus_investigation_info"."update_user" is
'�޸���';

comment on column "entrus_investigation_info"."update_date" is
'�޸�ʱ��';

comment on column "entrus_investigation_info"."delete_flag" is
'ɾ����ʶ';

comment on column "entrus_investigation_info"."rev1" is
'Ԥ��1';

comment on column "entrus_investigation_info"."rev2" is
'Ԥ��2';

comment on column "entrus_investigation_info"."rev3" is
'Ԥ��3';

comment on column "entrus_investigation_info"."rev4" is
'Ԥ��4';

comment on column "entrus_investigation_info"."rev5" is
'Ԥ��5';

comment on column "entrus_investigation_info"."rev6" is
'Ԥ��6';

comment on column "entrus_investigation_info"."rev7" is
'Ԥ��7';

comment on table "entrus_investigation_typeinfo" is
'ί��Э����Ϣ�����';

comment on column "entrus_investigation_typeinfo"."id" is
'id';

comment on column "entrus_investigation_typeinfo"."entrust_no" is
'ί�б��';

comment on column "entrus_investigation_typeinfo"."invest_type" is
'Э����Ŀ���';

comment on column "entrus_investigation_typeinfo"."invest_content" is
'Э����Ŀ����';

comment on column "entrus_investigation_typeinfo"."invest_desc" is
'Э����Ŀ˵��';

comment on column "entrus_investigation_typeinfo"."create_user" is
'������';

comment on column "entrus_investigation_typeinfo"."create_date" is
'����ʱ��';

comment on column "entrus_investigation_typeinfo"."update_user" is
'�޸���';

comment on column "entrus_investigation_typeinfo"."update_date" is
'�޸�ʱ��';

comment on column "entrus_investigation_typeinfo"."delete_flag" is
'ɾ����ʶ';

comment on column "entrus_investigation_typeinfo"."rev1" is
'Ԥ��1';

comment on column "entrus_investigation_typeinfo"."rev2" is
'Ԥ��2';

comment on column "entrus_investigation_typeinfo"."rev3" is
'Ԥ��3';

comment on column "entrus_investigation_typeinfo"."rev4" is
'Ԥ��4';

comment on column "entrus_investigation_typeinfo"."rev5" is
'Ԥ��5';

comment on column "entrus_investigation_typeinfo"."rev6" is
'Ԥ��6';

comment on column "entrus_investigation_typeinfo"."rev7" is
'Ԥ��7';

comment on table "entrust_base_info" is
'ί����Ϣ��';

comment on column "entrust_base_info"."id" is
'id';

comment on column "entrust_base_info"."entrust_no" is
'ί�б��';

comment on column "entrust_base_info"."entrust_ser" is
'ί�����';

comment on column "entrust_base_info"."entrust_number" is
'ί�д���';

comment on column "entrust_base_info"."entrust_type" is
'ί������';

comment on column "entrust_base_info"."province_code" is
'�참��λʡcode';

comment on column "entrust_base_info"."city_code" is
'�참��λ��code';

comment on column "entrust_base_info"."unit" is
'�참��λ';

comment on column "entrust_base_info"."person_name" is
'�참��';

comment on column "entrust_base_info"."telephone" is
'�참����ϵ�绰';

comment on column "entrust_base_info"."title" is
'�참��ְ��';

comment on column "entrust_base_info"."istake" is
'�Ƿ����˵�ȡ';

comment on column "entrust_base_info"."visitor_name" is
'����������';

comment on column "entrust_base_info"."visitor_telephone" is
'��������ϵ��ʽ';

comment on column "entrust_base_info"."visitor_unit" is
'�����˵�λ';

comment on column "entrust_base_info"."visitor_title" is
'������ְ��';

comment on column "entrust_base_info"."case_stage" is
'��������׶�';

comment on column "entrust_base_info"."is_reply" is
'�Ƿ���Ҫ�غ�';

comment on column "entrust_base_info"."memo" is
'��ע';

comment on column "entrust_base_info"."is_share_caseinfo" is
'�Ƿ�������Ϣ';

comment on column "entrust_base_info"."is_share_queryresult" is
'�Ƿ����ѯ���';

comment on column "entrust_base_info"."create_user" is
'������';

comment on column "entrust_base_info"."create_date" is
'����ʱ��';

comment on column "entrust_base_info"."update_user" is
'�޸���';

comment on column "entrust_base_info"."update_date" is
'�޸�ʱ��';

comment on column "entrust_base_info"."delete_flag" is
'ɾ����ʶ';

comment on column "entrust_base_info"."data_resource" is
'������Դ';

comment on column "entrust_base_info"."process_state" is
'����״̬';

comment on column "entrust_base_info"."rev1" is
'Ԥ��1';

comment on column "entrust_base_info"."rev2" is
'Ԥ��2';

comment on column "entrust_base_info"."rev3" is
'Ԥ��3';

comment on column "entrust_base_info"."rev4" is
'Ԥ��4';

comment on column "entrust_base_info"."rev5" is
'Ԥ��5';

comment on column "entrust_base_info"."rev6" is
'Ԥ��6';

comment on column "entrust_base_info"."rev7" is
'Ԥ��7';

comment on table "entrust_case_info" is
'ί�а�����Ϣ';

comment on column "entrust_case_info"."id" is
'id';

comment on column "entrust_case_info"."entrust_no" is
'ί�б��';

comment on column "entrust_case_info"."case_name" is
'��������';

comment on column "entrust_case_info"."case_occur_date" is
'����ʱ��';

comment on column "entrust_case_info"."case_no" is
'�������';

comment on column "entrust_case_info"."case_money_amout" is
'�������';

comment on column "entrust_case_info"."brand" is
'�永Ʒ��';

comment on column "entrust_case_info"."case_type" is
'��������';

comment on column "entrust_case_info"."case_info" is
'��Ҫ����';

comment on column "entrust_case_info"."create_user" is
'������';

comment on column "entrust_case_info"."create_date" is
'����ʱ��';

comment on column "entrust_case_info"."update_user" is
'�޸���';

comment on column "entrust_case_info"."update_date" is
'�޸�ʱ��';

comment on column "entrust_case_info"."delete_flag" is
'ɾ����ʶ';

comment on column "entrust_case_info"."is_end" is
'�Ƿ�᰸';

comment on column entrust_case_info.is_series is 
'�Ƿ񴮲�1(����)0(δ����)';

comment on column entrust_case_info.series_user is 
'������';

comment on column entrust_case_info.series_date is 
'����ʱ��';

comment on column "entrust_case_info"."rev1" is
'Ԥ��1';

comment on column "entrust_case_info"."rev2" is
'Ԥ��2';

comment on column "entrust_case_info"."rev3" is
'Ԥ��3';

comment on column "entrust_case_info"."rev4" is
'Ԥ��4';

comment on column "entrust_case_info"."rev5" is
'Ԥ��5';

comment on column "entrust_case_info"."rev6" is
'Ԥ��6';

comment on column "entrust_case_info"."rev7" is
'Ԥ��7';

comment on table "entrust_check_list" is
'ί����˱�';

comment on column "entrust_check_list"."id" is
'����';

comment on column "entrust_check_list"."entrust_no" is
'ί�б��';

comment on column "entrust_check_list"."check_key" is
'���״̬����';

comment on column "entrust_check_list"."check_user" is
'�����';

comment on column "entrust_check_list"."check_date" is
'���ʱ��';

comment on column "entrust_check_list"."check_ispass" is
'����Ƿ�ͨ��';

comment on column "entrust_check_list"."check_desdc" is
'���˵��';

comment on column "entrust_check_list"."is_exec" is
'�Ƿ�ִ��';

comment on column "entrust_check_list"."exec_no" is
'ִ�����';

comment on column "entrust_check_list"."rev1" is
'Ԥ��1';

comment on column "entrust_check_list"."rev2" is
'Ԥ��2';

comment on column "entrust_check_list"."rev3" is
'Ԥ��3';

comment on column "entrust_check_list"."rev4" is
'Ԥ��4';

comment on column "entrust_check_list"."rev5" is
'Ԥ��5';

comment on column "entrust_check_list"."rev6" is
'Ԥ��6';

comment on column "entrust_check_list"."rev7" is
'Ԥ��7';

comment on table "entrust_satisfactory_results" is
'ί�������������';

comment on column "entrust_satisfactory_results"."id" is
'����';

comment on column "entrust_satisfactory_results"."entrust_no" is
'ί�б��';

comment on column "entrust_satisfactory_results"."satisfactory_key" is
'������Ŀkey';

comment on column "entrust_satisfactory_results"."satisfactory_result" is
'��������';

comment on column "entrust_satisfactory_results"."type" is
'��Ŀ����';

comment on column "entrust_satisfactory_results"."advice" is
'����ͽ���';

comment on column "entrust_satisfactory_results"."create_user" is
'������';

comment on column "entrust_satisfactory_results"."create_date" is
'����ʱ��';

comment on column "entrust_satisfactory_results"."update_user" is
'�޸���';

comment on column "entrust_satisfactory_results"."update_date" is
'�޸�ʱ��';

comment on column "entrust_satisfactory_results"."delete_flag" is
'ɾ����ʶ';

comment on column "entrust_satisfactory_results"."rev1" is
'Ԥ��1';

comment on column "entrust_satisfactory_results"."rev2" is
'Ԥ��2';

comment on column "entrust_satisfactory_results"."rev3" is
'Ԥ��3';

comment on column "entrust_satisfactory_results"."rev4" is
'Ԥ��4';

comment on column "entrust_satisfactory_results"."rev5" is
'Ԥ��5';

comment on column "entrust_satisfactory_results"."rev6" is
'Ԥ��6';

comment on column "entrust_satisfactory_results"."rev7" is
'Ԥ��7';

comment on table "entrust_suspect_info" is
'������������Ϣ��';

comment on column "entrust_suspect_info"."id" is
'id';

comment on column "entrust_suspect_info"."entrust_no" is
'ί�б��';

comment on column "entrust_suspect_info"."suspect_name" is
'����������';

comment on column "entrust_suspect_info"."suspect_card_no" is
'���������֤';

comment on column "entrust_suspect_info"."suspect_telephone" is
'�������ֻ�';

comment on column "entrust_suspect_info"."suspect_taobao_no" is
'�������Ա�';

comment on column "entrust_suspect_info"."suspect_alipay" is
'������֧����';

comment on column "entrust_suspect_info"."suspect_qq" is
'������qq';

comment on column "entrust_suspect_info"."suspect_email" is
'����������';

comment on column "entrust_suspect_info"."create_user" is
'������';

comment on column "entrust_suspect_info"."create_date" is
'����ʱ��';

comment on column "entrust_suspect_info"."update_user" is
'�޸���';

comment on column "entrust_suspect_info"."update_date" is
'�޸�ʱ��';

comment on column "entrust_suspect_info"."delete_flag" is
'ɾ����ʶ';

comment on column "entrust_suspect_info"."rev1" is
'Ԥ��1';

comment on column "entrust_suspect_info"."rev2" is
'Ԥ��2';

comment on column "entrust_suspect_info"."rev3" is
'Ԥ��3';

comment on column "entrust_suspect_info"."rev4" is
'Ԥ��4';

comment on column "entrust_suspect_info"."rev5" is
'Ԥ��5';

comment on column "entrust_suspect_info"."rev6" is
'Ԥ��6';

comment on column "entrust_suspect_info"."rev7" is
'Ԥ��7';

comment on table "entrust_victim_info" is
'�����ܺ�����Ϣ��';

comment on column "entrust_victim_info"."id" is
'����';

comment on column "entrust_victim_info"."entrust_no" is
'ί�б��';

comment on column "entrust_victim_info"."victim_name" is
'�ܺ�������';

comment on column "entrust_victim_info"."victim_card_no" is
'�ܺ������֤';

comment on column "entrust_victim_info"."victim_telephone" is
'�ܺ����ֻ�';

comment on column "entrust_victim_info"."victim_taobao_no" is
'�ܺ����Ա��˺�';

comment on column "entrust_victim_info"."victim_alipay" is
'�ܺ���֧�����˺�';

comment on column "entrust_victim_info"."victim_address" is
'�ܺ��˵�ַ';

comment on column "entrust_victim_info"."create_user" is
'������';

comment on column "entrust_victim_info"."create_date" is
'����ʱ��';

comment on column "entrust_victim_info"."update_user" is
'�޸���';

comment on column "entrust_victim_info"."update_date" is
'�޸�ʱ��';

comment on column "entrust_victim_info"."delete_flag" is
'ɾ����ʶ';

comment on column "entrust_victim_info"."rev1" is
'Ԥ��1';

comment on column "entrust_victim_info"."rev2" is
'Ԥ��2';

comment on column "entrust_victim_info"."rev3" is
'Ԥ��3';

comment on column "entrust_victim_info"."rev4" is
'Ԥ��4';

comment on column "entrust_victim_info"."rev5" is
'Ԥ��5';

comment on column "entrust_victim_info"."rev6" is
'Ԥ��6';

comment on column "entrust_victim_info"."rev7" is
'Ԥ��7';

comment on table "log" is
'ϵͳ��־��';

comment on column "log"."id" is
'����';

comment on column "log"."userId" is
'������id';

comment on column "log"."username" is
'����������';

comment on column "log"."action" is
'��������';

comment on column "log"."module" is
'ģ�����';

comment on column "log"."actionTime" is
'��������';

comment on column "log"."userIP" is
'������ip';

comment on column "log"."operTime" is
'����ʱ��';

comment on column "log"."actiondesc" is
'��������';

comment on column "log"."rev1" is
'Ԥ��1';

comment on column "log"."rev2" is
'Ԥ��2';

comment on column "log"."rev3" is
'Ԥ��3';

comment on column "log"."rev4" is
'Ԥ��4';

comment on column "log"."rev5" is
'Ԥ��5';

comment on column "log"."rev6" is
'Ԥ��6';

comment on column "log"."rev7" is
'Ԥ��7';

comment on table "res_roles" is
'ϵͳ��ɫ��Դ��';

comment on column "res_roles"."role_id" is
'��ɫid';

comment on column "res_roles"."resc_id" is
'��Դid';

comment on column "res_roles"."rev1" is
'Ԥ��1';

comment on column "res_roles"."rev2" is
'Ԥ��2';

comment on column "res_roles"."rev3" is
'Ԥ��3';

comment on column "res_roles"."rev4" is
'Ԥ��4';

comment on column "res_roles"."rev5" is
'Ԥ��5';

comment on column "res_roles"."rev6" is
'Ԥ��6';

comment on column "res_roles"."rev7" is
'Ԥ��7';

comment on table "resources" is
'ϵͳ��Դ��';

comment on column "resources"."id" is
'����';

comment on column "resources"."name" is
'��Դ����';

comment on column "resources"."parentId" is
'�ϼ�id';

comment on column "resources"."resKey" is
'��Դ��ʶ';

comment on column "resources"."type" is
'����';

comment on column "resources"."resUrl" is
'��Դ��ַ';

comment on column "resources"."level" is
'����';

comment on column "resources"."description" is
'��ע';

comment on column "resources"."rev1" is
'Ԥ��1';

comment on column "resources"."rev2" is
'Ԥ��2';

comment on column "resources"."rev3" is
'Ԥ��3';

comment on column "resources"."rev4" is
'Ԥ��4';

comment on column "resources"."rev5" is
'Ԥ��5';

comment on column "resources"."rev6" is
'Ԥ��6';

comment on column "resources"."rev7" is
'Ԥ��7';

comment on table "role" is
'ϵͳ��ɫ��';

comment on column "role"."id" is
'����';

comment on column "role"."name" is
'��ɫ����';

comment on column "role"."description" is
'��ɫ˵��';

comment on column "role"."enable" is
'�Ƿ����';

comment on column "role"."roleKey" is
'��ɫ��־';

comment on column "role"."rev1" is
'Ԥ��1';

comment on column "role"."rev2" is
'Ԥ��2';

comment on column "role"."rev3" is
'Ԥ��3';

comment on column "role"."rev4" is
'Ԥ��4';

comment on column "role"."rev5" is
'Ԥ��5';

comment on column "role"."rev6" is
'Ԥ��6';

comment on column "role"."rev7" is
'Ԥ��7';

comment on table "series_case_alipay" is
'����������֧����';

comment on column "series_case_alipay"."id" is
'����';

comment on column "series_case_alipay"."entrust_case_id" is
'����id';

comment on column "series_case_alipay"."alipay_card" is
'֧�����˺�';

comment on column "series_case_alipay"."create_user" is
'������';

comment on column "series_case_alipay"."create_date" is
'����ʱ��';

comment on column "series_case_alipay"."update_user" is
'�޸���';

comment on column "series_case_alipay"."update_date" is
'�޸�ʱ��';

comment on column "series_case_alipay"."rev1" is
'Ԥ��1';

comment on column "series_case_alipay"."rev2" is
'Ԥ��2';

comment on column "series_case_alipay"."rev3" is
'Ԥ��3';

comment on column "series_case_alipay"."rev4" is
'Ԥ��4';

comment on column "series_case_alipay"."rev5" is
'Ԥ��5';

comment on column "series_case_alipay"."rev6" is
'Ԥ��6';

comment on column "series_case_alipay"."rev7" is
'Ԥ��7';

comment on table "series_case_bankcard" is
'�������������п���';

comment on column "series_case_bankcard"."id" is
'����';

comment on column "series_case_bankcard"."entrust_case_id" is
'����id';

comment on column "series_case_bankcard"."bankcard" is
'���п���';

comment on column "series_case_bankcard"."create_user" is
'������';

comment on column "series_case_bankcard"."create_date" is
'����ʱ��';

comment on column "series_case_bankcard"."update_user" is
'�޸���';

comment on column "series_case_bankcard"."update_date" is
'�޸�ʱ��';

comment on column "series_case_bankcard"."rev1" is
'Ԥ��1';

comment on column "series_case_bankcard"."rev2" is
'Ԥ��2';

comment on column "series_case_bankcard"."rev3" is
'Ԥ��3';

comment on column "series_case_bankcard"."rev4" is
'Ԥ��4';

comment on column "series_case_bankcard"."rev5" is
'Ԥ��5';

comment on column "series_case_bankcard"."rev6" is
'Ԥ��6';

comment on column "series_case_bankcard"."rev7" is
'Ԥ��7';

comment on table "series_case_certificate" is
'����������֤����';

comment on column "series_case_certificate"."id" is
'����';

comment on column "series_case_certificate"."entrust_case_id" is
'����id';

comment on column "series_case_certificate"."certificate_no" is
'֤����';

comment on column "series_case_certificate"."create_user" is
'������';

comment on column "series_case_certificate"."create_date" is
'����ʱ��';

comment on column "series_case_certificate"."update_user" is
'�޸���';

comment on column "series_case_certificate"."update_date" is
'�޸�ʱ��';

comment on column "series_case_certificate"."rev1" is
'Ԥ��1';

comment on column "series_case_certificate"."rev2" is
'Ԥ��2';

comment on column "series_case_certificate"."rev3" is
'Ԥ��3';

comment on column "series_case_certificate"."rev4" is
'Ԥ��4';

comment on column "series_case_certificate"."rev5" is
'Ԥ��5';

comment on column "series_case_certificate"."rev6" is
'Ԥ��6';

comment on column "series_case_certificate"."rev7" is
'Ԥ��7';

comment on table "series_case_detail" is
'�����������';

comment on column "series_case_detail"."id" is
'����';

comment on column "series_case_detail"."series_case_id" is
'������id';

comment on column "series_case_detail"."case_id" is
'�������';

comment on column "series_case_detail"."entrust_no" is
'ί�б��';

comment on column "series_case_detail"."series_person" is
'��������';

comment on column "series_case_detail"."create_user" is
'������';

comment on column "series_case_detail"."create_date" is
'����ʱ��';

comment on column "series_case_detail"."update_user" is
'�޸���';

comment on column "series_case_detail"."update_date" is
'�޸�ʱ��';

comment on column "series_case_detail"."delete_flag" is
'ɾ����ʶ';

comment on column "series_case_detail"."rev1" is
'Ԥ��1';

comment on column "series_case_detail"."rev2" is
'Ԥ��2';

comment on column "series_case_detail"."rev3" is
'Ԥ��3';

comment on column "series_case_detail"."rev4" is
'Ԥ��4';

comment on column "series_case_detail"."rev5" is
'Ԥ��5';

comment on column "series_case_detail"."rev6" is
'Ԥ��6';

comment on column "series_case_detail"."rev7" is
'Ԥ��7';

comment on table "series_case_info" is
'��������Ϣ��';

comment on column "series_case_info"."id" is
'����';

comment on column "series_case_info"."series_case_no" is
'���������';

comment on column "series_case_info"."series_case_name" is
'����������';

comment on column "series_case_info"."series_reason" is
'��������';

comment on column "series_case_info"."series_desc" is
'����������';

comment on column "series_case_info"."create_user" is
'������';

comment on column "series_case_info"."create_date" is
'����ʱ��';

comment on column "series_case_info"."update_user" is
'�޸���';

comment on column "series_case_info"."update_date" is
'�޸�ʱ��';

comment on column "series_case_info"."delete_flag" is
'ɾ����ʶ';

comment on column "series_case_info"."rev1" is
'Ԥ��1';

comment on column "series_case_info"."rev2" is
'Ԥ��2';

comment on column "series_case_info"."rev3" is
'Ԥ��3';

comment on column "series_case_info"."rev4" is
'Ԥ��4';

comment on column "series_case_info"."rev5" is
'Ԥ��5';

comment on column "series_case_info"."rev6" is
'Ԥ��6';

comment on column "series_case_info"."rev7" is
'Ԥ��7';

comment on table "series_case_telephone" is
'���������ݵ绰����';

comment on column "series_case_telephone"."id" is
'����';

comment on column "series_case_telephone"."entrust_case_id" is
'����id';

comment on column "series_case_telephone"."telephone" is
'�ֻ�����';

comment on column "series_case_telephone"."create_user" is
'������';

comment on column "series_case_telephone"."create_date" is
'����ʱ��';

comment on column "series_case_telephone"."update_user" is
'�޸���';

comment on column "series_case_telephone"."update_date" is
'�޸�ʱ��';

comment on column "series_case_telephone"."rev1" is
'Ԥ��1';

comment on column "series_case_telephone"."rev2" is
'Ԥ��2';

comment on column "series_case_telephone"."rev3" is
'Ԥ��3';

comment on column "series_case_telephone"."rev4" is
'Ԥ��4';

comment on column "series_case_telephone"."rev5" is
'Ԥ��5';

comment on column "series_case_telephone"."rev6" is
'Ԥ��6';

comment on column "series_case_telephone"."rev7" is
'Ԥ��7';

