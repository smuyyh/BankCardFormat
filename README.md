# BankCardFormat

## 自动格式化银行卡号的EditText，每四位增加一个空格，并根据银行卡号判断该银行卡归属的银行及卡别

```xml
<com.example.library.BandCardEditText
    android:id="@+id/et"
    android:layout_width="match_parent"
    android:layout_height="30dp"
    android:text="622700187301032701"
    android:background="#cccccc"/>
```
```java
  tv = (TextView) findViewById(R.id.tv);
  editText = (BandCardEditText) findViewById(R.id.et);
  editText.setBankCardListener(new BandCardEditText.BankCardListener() {
      @Override
      public void success(String name) {
          tv.setText("所属银行：" + name);
      }
  
      @Override
      public void failure() {
          tv.setText("所属银行：");
      }
  });
```

![image](https://github.com/smuyyh/BankCardFormat/blob/master/screenshot/device.png?raw=true =100)

