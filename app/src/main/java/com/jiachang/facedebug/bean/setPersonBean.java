package com.jiachang.facedebug.bean;

/**
 * @author Mickey.Ma
 * @date 2020-01-07
 * @description
 */
public class setPersonBean {

    private DataBean data ;
    private String msg;
    private int result;
    private boolean success;


    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    private class DataBean {
        private String createTime;
        private String id;
        private String idcardNum;
        private String name;
        private String IDNumber;
        private int facePermission;
        private int idCardPermission;
        private int faceAndCardPermission;
        private int IDPermission;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        protected String getId() {
            return id;
        }

        protected void setId(String id) {
            this.id = id;
        }

        public String getIdcardNum() {
            return idcardNum;
        }

        public void setIdcardNum(String idcardNum) {
            this.idcardNum = idcardNum;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIDNumber() {
            return IDNumber;
        }

        public void setIDNumber(String IDNumber) {
            this.IDNumber = IDNumber;
        }

        public int getFacePermission() {
            return facePermission;
        }

        public void setFacePermission(int facePermission) {
            this.facePermission = facePermission;
        }

        public int getIdCardPermission() {
            return idCardPermission;
        }

        public void setIdCardPermission(int idCardPermission) {
            this.idCardPermission = idCardPermission;
        }

        public int getFaceAndCardPermission() {
            return faceAndCardPermission;
        }

        public void setFaceAndCardPermission(int faceAndCardPermission) {
            this.faceAndCardPermission = faceAndCardPermission;
        }

        public int getIDPermission() {
            return IDPermission;
        }

        public void setIDPermission(int IDPermission) {
            this.IDPermission = IDPermission;
        }
    }
}
