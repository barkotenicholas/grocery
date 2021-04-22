package nico.example.grocery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.example.grocery.R;

public class ProductDetails extends AppCompatActivity {
    ImageView imageViewProduct, imageViewBack;
    TextView txtName, txtDesc, txtPrice;
    Button button;
    private BillingProcessor billingProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent i =getIntent();
        String name = i.getStringExtra("name");
        String desc = i.getStringExtra("desc");
        String price = i.getStringExtra("price");
        int image = i.getIntExtra("image",R.drawable.b1);
        txtName = findViewById(R.id.lblPName);
        txtDesc = findViewById(R.id.lblPDesc);
        txtPrice = findViewById(R.id.lblPPrice);
        imageViewProduct= findViewById(R.id.imgProduct);
        imageViewBack = findViewById(R.id.imgBack);
        button = findViewById(R.id.btnBuyNow);
        txtName.setText(name);
        txtPrice.setText(price);
        txtDesc.setText(desc);
        imageViewProduct.setImageResource(image);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(ProductDetails.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        billingProcessor = new BillingProcessor(this,"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAimYOGnBuxZXnU5GCiXsaWdSFW3ToKhiEOB25l1GvbGAVKdOksfAfkWFbi3aFz39Xpl61Ef7K/0kmUcb2yYBA4olyW8rFhlpRtIi1s4oIm1ZIaWUZ730jnejctr8XWVEFFCtnLbh9gS1wuzB4txu5xM1mjs3rQAZ1jO7NL96s1wwoFm30a9iNPxsUcEHTF/Dho+ufvXKnAGu8/SqVm3erQFzL0sTST/AY4Yw4o2ViDxqqe2l69GlJgYu9T7ccf/ZahQM25bS4v71iD5LrRMwQjDc4528UbWn6iqJCsKeS8cCICc3Oj5CLTJ/Pb12DbvfKkbdf0/LwQpn8HDguH9zhCQIDAQAB" , null, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(String productId, TransactionDetails details) {
                Toast.makeText(ProductDetails.this,"purchased",Toast.LENGTH_LONG);

            }

            @Override
            public void onPurchaseHistoryRestored() {

            }

            @Override
            public void onBillingError(int errorCode, Throwable error) {

            }

            @Override
            public void onBillingInitialized() {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProductDetails.this,"Clicked",Toast.LENGTH_SHORT).show();
                billingProcessor.subscribe(ProductDetails.this,"acup");

            }
        });
    }
}