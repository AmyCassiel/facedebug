package com.jiachang.facedebug.bean;

/**
 * @author Mickey.Ma
 * @date 2020-01-13
 * @description
 */
public class setNetInfoBean {
    private DataBean data ;
    private String msg;
    private int result;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

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

    private class DataBean {
        private String dHCPMod;
        private String dNS1;
        private String dNS2;
        private String gateway;
        private String ip;
        private String subnetMask;

        public String getdHCPMod() {
            return dHCPMod;
        }

        public void setdHCPMod(String dHCPMod) {
            this.dHCPMod = dHCPMod;
        }

        public String getdNS1() {
            return dNS1;
        }

        public void setdNS1(String dNS1) {
            this.dNS1 = dNS1;
        }

        public String getdNS2() {
            return dNS2;
        }

        public void setdNS2(String dNS2) {
            this.dNS2 = dNS2;
        }

        public String getGateway() {
            return gateway;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getSubnetMask() {
            return subnetMask;
        }

        public void setSubnetMask(String subnetMask) {
            this.subnetMask = subnetMask;
        }
    }
}
