package com.grandblue.dingding.service;

import lombok.Data;

@Data
public class Output {
    //惠安3号炉二次风频率推荐值
    private String HA3_ECFPL_OUTPUT ="1";

    //惠安3号炉炉膛中上部温度取中后均值
    private String HA3_LTWD_HANDLE ="2";

    //惠安3号炉逆推风门3中值
    private String HA3_NTFM03_HANDLE="1";

    //惠安3号炉逆推风门4中值
    private String HA3_NTFM04_HANDLE="1";

    //惠安3号炉逆推风门1推荐值
    private String HA3_NTFM1_OUTPUT="1";

    //惠安3号炉逆推风门2推荐值
    private String HA3_NTFM2_OUTPUT="1";

    //惠安3号炉逆推风门3推荐值
    private String HA3_NTFM3_OUTPUT="1";

    //惠安3号炉逆推风门4推荐值
    private String HA3_NTFM4_OUTPUT="1";

    //惠安3号炉逆推炉排间隔停推荐值
    private String HA3_NTLPJGT_OUTPUT="1";

    //惠安3号炉汽包水位中值
    private String HA3_QBSW_HANDLE="1";

    //惠安3号炉顺推风门推荐值
    private String HA3_STFM_OUTPUT="1";

    //惠安3号炉顺推炉排间隔停推荐值
    private String HA3_STLPJGT_OUTPUT="1";

    //惠安3号炉推料器间隔停推荐值
    private String HA3_TLQJGT_OUTPUT="1";

    //惠安3号炉一次风频率推荐值
    private String HA3_YCFPL_OUTPUT="1";

    //惠安3号炉蒸汽抽汽阀门推荐值
    private String HA3_ZQCQFM_OUTPUT="1";

    //烟道出口烟气含氧量
    private String HAARCA_321="1";

    //二次风机电流HNECF_301CE
    private String HAECF_301CE="1";

    //二次风机变频频率反馈
    private String HAECF_301PL="1";

    //一风机进口空气流量
    private String HAFRCY_307="1";

    //二次风机出口空气流量HNFRCY_308
    private String HAFRCY_308="1";

    //过热蒸汽流量HNFRQ_304
    private String HAFRQ_304="1";

    //#3CO
    private String HALED_CO3="1";

    //#3HCL
    private String HALED_HCL="1";

    //#3NOX
    private String HALED_NOX3="1";

    //#3SO2
    private String HALED_SO23="1";

    //#3炉逆推风门#1反馈
    private String HANTFM1_03="1";

    //#3炉逆推风门#2反馈
    private String HANTFM2_03="1";

    //#3炉顺推风门反馈
    private String HANTFMFK_03="1";

    //过热蒸汽压力
    private String HAPI_310="1";

    //汽包压力A
    private String HAPRA_306A="1";

    //#3炉逆推炉排间隔停时间
    private String HASw3NTJG_G="1";

    //#3炉顺推间隔关时间
    private String HASw3STJG_G="1";

    //#3炉一次风蒸汽加热器抽汽阀门指令PV
    private String HATIC326PV="1";

    //#3炉推料器间隔停时间3
    private String HATLQ_SJ5="1";

    //过热器出口集箱汽温HNTRCA_310A
    private String HATRCA_310A="1";

    //#3炉一次风蒸汽加热器出口风温
    private String HATRC_314="1";

    //一风机轴承振动1
    private String HAVIA_310A="1";

    //二次风机轴承振动1HNVIA_311A
    private String HAVIA_311A="1";

    //引风机轴承振动1HNVIA_312A
    private String HAVIA_312A="1";

    //一风机电流
    private String HAYCF_301CE="1";

    //一风机变频频率反馈
    private String HAYCF_301PL="1";

    //引风机电流HNYFJ_301CE
    private String HAYFJ_301CE="1";

    //引风机电流HNYFJ_301CE
    private String HAYFJ_301PL="1";
}
