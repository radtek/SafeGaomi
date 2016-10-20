ALTER TABLE `entrust_case_info`
ADD COLUMN `is_series`  varchar(2) NULL COMMENT '是否串并1(串并)0(未串并)',
ADD COLUMN `series_user`  int NULL COMMENT '串并人',
ADD COLUMN `series_date`  timestamp NULL COMMENT '串并时间';

