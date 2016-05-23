package cn.elife.utils;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;
import java.util.List;

import cn.elife.bean.Cw;
import cn.elife.bean.Sentence;
import cn.elife.bean.Ws;
import cn.elife.interfaces.MscInterface;

/**
 * Created by wgyscsf on 2016/5/20.
 */


/**
 * 该工具类可以实现快速调用科大讯飞的语音识别，获取识别结果。【采用回调的方式实现】
 * 使用方式：1.在你的类实例化该类，然后调用该工具类的getVoiceReslut方法，参数:"你的类.this"(说法不规范，好理解=_+)。
 * 2.让你的类实现MscInterface接口，在实现的接口中获取参数即可；
 * 参数说明：参数为一个List<String>，共两个值，用strList.get(0)和strList.get(1)获取。
 * 第一个是识别的完整的语句，如；“今天你吃饭了吗？”；
 * 第二个是识别的分词结果，以“,”隔开，如“今天,你,吃饭,了,吗,?,”
 */
public class MSCUtils {
    private final String TAG = "MSCUtils+++++";
    private MscInterface mMscInterface;
    private List<String> chineseWordList = new ArrayList<String>();//分词结果集
    private String Str = "";//最终形成的句子
    private String str2 = "";//要做的分词效果

    public void getVoiceReslut(final MscInterface mMscInterface) {
       this.mMscInterface = mMscInterface;
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog((Context) mMscInterface, new InitListener() {
            @Override
            public void onInit(int i) {

            }
        });
        //2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
        //结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener(new RecognizerDialogListener() {
            //特别注意该方法会回调多次，不要让数据覆盖了
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean b) {
                String jsonObg = recognizerResult.getResultString();
                Gson gson = new Gson();
                Sentence sentence = gson.fromJson(jsonObg, new Sentence().getClass());
                List<Ws> wsList = sentence.getWs();
                for (Ws w : wsList) {
                    List<Cw> cwList = w.getCw();
                    for (Cw c : cwList) {
                        String chineseWord = c.getW();
                        Str += chineseWord;
                        str2 += chineseWord + ",";
                        Log.e(TAG, Str);
                        Log.e(TAG, str2);
                    }
                }
                if (b) processVoice();//b然后true,表示识别结束，当时别结束，我们再回调

            }

            @Override
            public void onError(SpeechError speechError) {

            }
        });
        //4.显示dialog，接收语音输入
        mDialog.show();
    }

    private void processVoice() {
        chineseWordList.clear();
        //最后再填充数据，防止数据被覆盖
        chineseWordList.add(Str);
        chineseWordList.add(str2);
        mMscInterface.voice2List(chineseWordList);//开始回调
    }

}
