package cn.elife.interfaces;

import java.util.List;

/**
 * Created by wgyscsf on 2016/5/20.
 */

/**
 * 该接口是为了获取语音识别结果。
 * 参数为一个List<String>，共两个值，用strList.get(0)和strList.get(1)获取。
 * 第一个是识别的完整的语句，如；“今天你吃饭了吗？”；第二个是识别的分词结果，以“,”隔开，如“今天,你,吃饭,了,吗,?,”
 */
public interface MscInterface {
    public  void voice2List(List<String> strList);
}
