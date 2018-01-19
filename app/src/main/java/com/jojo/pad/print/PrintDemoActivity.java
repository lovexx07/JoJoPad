package com.jojo.pad.print;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jojo.pad.R;
import com.jojo.pad.util.ThreadPoolManager;
import com.jojo.pad.util.printerCmdUtils;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrintDemoActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    private UsbPrinter usbprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_demo);
        ButterKnife.bind(this);

        usbprint = UsbPrinter.getInstance();
        usbprint.init(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            usbprint.sendMessageToPoint(clientPaper());
                        } catch (Exception e) {
                        }
                    }
                });
            }
        });
    }

    public byte[] clientPaper() {
        try {
            byte[] init_printer = printerCmdUtils.init_printer();
            byte[] next2Line = printerCmdUtils.nextLine(2);
            byte[] next4Line = printerCmdUtils.nextLine(4);
            byte[] title = "出餐单（午餐）彭鑫的店".getBytes("gb2312");
            byte[] boldOn = printerCmdUtils.boldOn();
            byte[] fontSize2Big = printerCmdUtils.fontSizeSetBig(3);
            byte[] center = printerCmdUtils.alignCenter();
            byte[] Focus = "网 507".getBytes("gb2312");
            byte[] boldOff = printerCmdUtils.boldOff();
            byte[] fontSize2Small = printerCmdUtils.fontSizeSetSmall(3);
            byte[] left = printerCmdUtils.alignLeft();
            byte[] orderSerinum = "订单编号：11234".getBytes("gb2312");
            byte[] fontSize1Big = printerCmdUtils.fontSizeSetBig(2);
            byte[] FocusOrderContent = "韭菜鸡蛋饺子-小份（单）".getBytes("gb2312");
            byte[] fontSize1Small = printerCmdUtils.fontSizeSetSmall(2);
            byte[] priceInfo = "应收:22元 优惠：2.5元 ".getBytes("gb2312");
            byte[] nextLine = printerCmdUtils.nextLine(1);
            byte[] priceShouldPay = "实收:19.5元".getBytes("gb2312");
            byte[] takeTime = "取餐时间:2015-02-13 12:51:59".getBytes("gb2312");
            byte[] setOrderTime = "下单时间：2015-02-13 12:35:15".getBytes("gb2312");
            byte[] tips_1 = "微信关注'**'自助下单每天免1元".getBytes("gb2312");
            byte[] tips_2 = "饭后点评再奖5毛".getBytes("gb2312");
            byte[] line = "----------------------------,".getBytes("gb2312");
            byte[] breakPartial = printerCmdUtils.feedPaperCutAll();
            byte[][] cmdBytes = {
                    init_printer,
                    title, nextLine,
                    center, boldOn, fontSize2Big, Focus, boldOff, fontSize2Small, next2Line,
                    left, orderSerinum, nextLine,
                    center, boldOn, fontSize1Big, FocusOrderContent, boldOff, fontSize1Small, nextLine,
                    left, next2Line,
                    priceInfo, nextLine,
                    priceShouldPay, next2Line,
                    line,
                    takeTime, nextLine,
                    setOrderTime, next2Line,
                    line,
                    center, tips_1, nextLine,
                    center, tips_2, next4Line,
                    breakPartial
            };
            return printerCmdUtils.byteMerger(cmdBytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        usbprint.onDestory();
    }


}
