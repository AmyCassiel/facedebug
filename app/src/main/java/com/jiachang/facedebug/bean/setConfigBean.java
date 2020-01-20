package com.jiachang.facedebug.bean;

/**
 * @author Mickey.Ma
 * @date 2020-01-15
 * @description
 */
public class setConfigBean {
/*"data": {
"companyName":"上海家畅智能科技有限公司";
"identifyDistance":1;//识别距离，默认 0：无限制，1：0.5 米以内，2：1 米以内，3：1.5 米以内，4：2 米以内，5：3 米以内，6：4 米以内。一代设备设置 0-6 档均有效；二代设备若 recRank 设置为 1，则识别距离可设置 0-6 档，若 recRank 设置为 2 或 3，则识别距离仅可设置 0-3 档
"identifyScores":80;//识别分数，识别命中分数，默认为 80，传入值务必在 60-100 之间
"saveIdentifyTime":0;//N 秒钟只保存一条识别记录配置，用于配置N 秒内多次识别只记录一次，默认为 0：每次识别记录都会计入数据库
"ttsModType":1;//语音模式类型，默认 1：不需要语音播报，2：播报名字，... 100：自定义
"ttsModContent":"{name}欢迎光临";//语音播报模式自定义内容，模板中只允许{name}字段，字段格式固定； 模板中只允许数字、英文、中文和“{”、“}”；内容长度限制 255 个字，请自行调整。例：{name}欢迎光临
"displayModType":1;//屏幕显示模式类型，默认 1：显示名字，2：关闭识别弹窗; 100： 自定义
"displayModContent":{name}，签到成功！;//显示模式自定义内容，模板中只允许{name}字段，字段格式固定；模板中只允许数字、中英文、中英文符号和“{”、“}”；内容长度限制 255 个字， 请自行调整。例：{name}，签到成功！
"comModType":1;//串口模式类型，默认 1：开门，2：不输出，3：输出人员 id，4：输出身份证/IC 卡号 idcardNum，... 100：自定义
"comModContent":#26WG{idcardNum}#;//串口模式自定义输出内容，模板中只允许{id}、{idcardNum}字段， 字段格式固定；模板中只允许英文和英文符号；内容长度限制 255 个字符，请自行调整。例（韦根输出）：#26WG{idcardNum}#或#26WG{id}#（其中 idcardNum、id 可配置的范围必须为 1-65535，否则信号输出失效；传空，则韦根不输出；若使用串口输出韦根信号，设备串口需外接定制的信号转换小板
"wg";//韦根口输出韦根信号（非串口），目前只支持输出 idcardNum 或 id，传参格式为#26WG{idcardNum}#或#26WG{id}#，传空，则韦根不输出。一代设备只能通过自定义串口输出配置韦根输出，二代设备既可通过串口配置韦根输出，也可使用此参数配置韦根口输出韦根信号（目前只支持韦根 26，idcardNum、id 可配置的范围必须为1-65535，否则信号输出失效）
"slogan";//标语，大屏展示
"intro";//公司简介，大屏展示
"recStrangerType";//陌生人开关（是否进行陌生人识），默认 1：不识别陌生人，2： 识别陌生人
"recStrangerTimesThreshold";//设备判定某人为陌生人所需时间等级（陌生人开关打开情况下设置有效），默认 3；1 表示快速判定但精确率最低，随着数值增加，判定时间增加，精确度提高生人识），1：不识别陌生人，2：识别陌生人"ttsModStrangerType";//陌生人语音模式类型（陌生人开关打开情况下设置有效），默认 1：不需要语音播报；2：陌生人警报；	100：自定义
"ttsModStrangerContent";//陌生人语音播报自定义内容，默认 1：模板中只允许数字、英文和中文；2：内容长度限制 255 个字。例：注意陌生人
"displayModStrangerType";//开启识别陌生人后，参数为 100 时，识别陌生人可以开启自定义弹窗显示内容
"displayModStrangerContent";//当 displayModStrangerType 参数为 100 时，陌生人自定义显示内容，不能为空。
"multiplayerDetection";//多个人脸检测设置，默认 1：检测多个人脸并进行识别，2：只检测多个人脸中最大的人脸，并进行识别
"recRank";//识别等级，1：识别速度最快，精确率最低；2：识别速度较快，精确率较高；3：识别速度较慢，精确度最高。此参数仅对二代设备有效，二代设备默认等级 2； 一代设备此参数无效，默认等级 1
"whitelist":1;//添加人证比对白名单开关；类型：int； 1：关；2：开; 白名单开关，若打开， 读取身份证信息后将身份证号与数据库内的所有人员的身份证号比对，若存在则进行人证比对流程；若不存在，则提示权限不足。若白名单开关关闭，读取的身份证信息后直接进行人证比对流程。
"isOpenRelay";//2 代继电器控制开关。类型：int；1：输出；2：不输出；默认为 1}
"result": 1,//接口调通
"success": true//设备配置设置成功
*/
    private DataBean data ;
    private int result;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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
        private String companyName;
        private float identifyDistance;
        private int identifyScores;
        private int saveIdentifyTime;
        private int ttsModType;
        private String ttsModContent;
        private int displayModType;
        private String displayModContent;
        private int comModType;
        private String comModContent;
        private String wg;
        private String slogan;
        private String intro;
        private int recStrangerType;
        private int recStrangerTimesThreshold;
        private String ttsModStrangerContent;
        private int displayModStrangerType;
        private String displayModStrangerContent;
        private int multiplayerDetection;
        private int recRank;
        private int whitelist;
        private int isOpenRelay;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public float getIdentifyDistance() {
            return identifyDistance;
        }

        public void setIdentifyDistance(float identifyDistance) {
            this.identifyDistance = identifyDistance;
        }

        public int getIdentifyScores() {
            return identifyScores;
        }

        public void setIdentifyScores(int identifyScores) {
            this.identifyScores = identifyScores;
        }

        public int getSaveIdentifyTime() {
            return saveIdentifyTime;
        }

        public void setSaveIdentifyTime(int saveIdentifyTime) {
            this.saveIdentifyTime = saveIdentifyTime;
        }

        public int getTtsModType() {
            return ttsModType;
        }

        public void setTtsModType(int ttsModType) {
            this.ttsModType = ttsModType;
        }

        public String getTtsModContent() {
            return ttsModContent;
        }

        public void setTtsModContent(String ttsModContent) {
            this.ttsModContent = ttsModContent;
        }

        public int getDisplayModType() {
            return displayModType;
        }

        public void setDisplayModType(int displayModType) {
            this.displayModType = displayModType;
        }

        public String getDisplayModContent() {
            return displayModContent;
        }

        public void setDisplayModContent(String displayModContent) {
            this.displayModContent = displayModContent;
        }

        public int getComModType() {
            return comModType;
        }

        public void setComModType(int comModType) {
            this.comModType = comModType;
        }

        public String getComModContent() {
            return comModContent;
        }

        public void setComModContent(String comModContent) {
            this.comModContent = comModContent;
        }

        public String getWg() {
            return wg;
        }

        public void setWg(String wg) {
            this.wg = wg;
        }

        public String getSlogan() {
            return slogan;
        }

        public void setSlogan(String slogan) {
            this.slogan = slogan;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getRecStrangerType() {
            return recStrangerType;
        }

        public void setRecStrangerType(int recStrangerType) {
            this.recStrangerType = recStrangerType;
        }

        public int getRecStrangerTimesThreshold() {
            return recStrangerTimesThreshold;
        }

        public void setRecStrangerTimesThreshold(int recStrangerTimesThreshold) {
            this.recStrangerTimesThreshold = recStrangerTimesThreshold;
        }

        public String getTtsModStrangerContent() {
            return ttsModStrangerContent;
        }

        public void setTtsModStrangerContent(String ttsModStrangerContent) {
            this.ttsModStrangerContent = ttsModStrangerContent;
        }

        public int getDisplayModStrangerType() {
            return displayModStrangerType;
        }

        public void setDisplayModStrangerType(int displayModStrangerType) {
            this.displayModStrangerType = displayModStrangerType;
        }

        public String getDisplayModStrangerContent() {
            return displayModStrangerContent;
        }

        public void setDisplayModStrangerContent(String displayModStrangerContent) {
            this.displayModStrangerContent = displayModStrangerContent;
        }

        public int getMultiplayerDetection() {
            return multiplayerDetection;
        }

        public void setMultiplayerDetection(int multiplayerDetection) {
            this.multiplayerDetection = multiplayerDetection;
        }

        public int getRecRank() {
            return recRank;
        }

        public void setRecRank(int recRank) {
            this.recRank = recRank;
        }

        public int getWhitelist() {
            return whitelist;
        }

        public void setWhitelist(int whitelist) {
            this.whitelist = whitelist;
        }

        public int getIsOpenRelay() {
            return isOpenRelay;
        }

        public void setIsOpenRelay(int isOpenRelay) {
            this.isOpenRelay = isOpenRelay;
        }
    }
}
