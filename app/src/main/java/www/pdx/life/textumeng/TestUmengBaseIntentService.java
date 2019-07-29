package www.pdx.life.textumeng;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.umeng.message.UmengBaseIntentService;

import org.android.agoo.common.AgooConstants;

public class TestUmengBaseIntentService extends UmengBaseIntentService {

    @Override
    protected void onMessage(Context context, Intent intent) {
        super.onMessage(context, intent);
       Log.e("1234",""+intent.getStringExtra(AgooConstants.MESSAGE_BODY));


    }
}
