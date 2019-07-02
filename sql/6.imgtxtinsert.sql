-- image update
update movie set movieImage = (select img_blob from IMG_TEST where no =1) where movienum = 1001;
update movie set movieImage = (select img_blob from IMG_TEST where no =2) where movienum = 1002;
update movie set movieImage = (select img_blob from IMG_TEST where no =3) where movienum = 1003;

--txt update
update movie set synopsis = (select txt_clob from TXT_TEST where no =1) where movienum = 1001;
update movie set synopsis = (select txt_clob from TXT_TEST where no =2) where movienum = 1002;
update movie set synopsis = (select txt_clob from TXT_TEST where no =3) where movienum = 1003;