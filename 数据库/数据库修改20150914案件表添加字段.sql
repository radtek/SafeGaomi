ALTER TABLE `entrust_case_info`
ADD COLUMN `is_series`  varchar(2) NULL COMMENT '�Ƿ񴮲�1(����)0(δ����)',
ADD COLUMN `series_user`  int NULL COMMENT '������',
ADD COLUMN `series_date`  timestamp NULL COMMENT '����ʱ��';

