package com.example.ifixhome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifixhome.Faq;
import com.example.ifixhome.FaqAdapter;
import com.example.ifixhome.R;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Faq> faqList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        recyclerView = findViewById(R.id.recyclerView);

        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        FaqAdapter FaqAdapter = new FaqAdapter(faqList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(FaqAdapter);
    }


    private void initData() {
        faqList = new ArrayList<>();
        faqList.add(new Faq("Is your service center accredited?", "Yes, our service center is accredited by the Electronics Service Industry Regulatory Authority (ESIRA). This accreditation ensures that our services meet the highest industry standards and undergo regular audits for quality assurance."));

        faqList.add(new Faq("What are your Home Appliance Service Packages?", "Our Home Appliance Service Packages are comprehensive plans designed to address various needs of your electronic appliances. These packages cover routine maintenance, repair services, and even replacement options, ensuring your appliances are always in top condition."));

        faqList.add(new Faq("What is included in a Maintenance Plan?", "Our Maintenance Plans include regular inspections, cleaning, and preventive maintenance measures to keep your appliances running smoothly and extend their lifespan."));

        faqList.add(new Faq("How long does it take to repair appliances?", "The repair duration depends on the type and complexity of the issue. Our technicians strive to provide prompt service, and most repairs are completed within 24 to 48 hours."));

        faqList.add(new Faq("How can I schedule a service appointment?", "You can schedule a service appointment by calling our hotline or booking online through our website. Our customer service team will assist you in arranging a convenient appointment slot."));

        faqList.add(new Faq("Do you offer warranty on repairs?", "Yes, we provide a warranty on our repair services. The duration of the warranty varies depending on the type of repair and the parts replaced. Our goal is to ensure your satisfaction and peace of mind."));

        faqList.add(new Faq("Can I track the status of my repair?", "Yes, you can track the status of your repair online through our website or mobile app. Simply log in to your account and view the current progress of your service request."));

        faqList.add(new Faq("Are your technicians certified?", "Yes, our technicians undergo rigorous training and certification programs to ensure they are qualified to handle various appliance repairs. You can trust our team to provide professional and reliable service."));

        faqList.add(new Faq("Do you offer emergency repair services?", "Yes, we understand that some appliance issues require immediate attention. That's why we offer emergency repair services for urgent situations. Contact us, and we'll prioritize your request."));

        faqList.add(new Faq("Where are your repair facilities located?", "We have state-of-the-art repair facilities located in strategic areas to serve our customers efficiently. Our facilities are equipped with advanced tools and equipment to handle a wide range of appliance repairs."));

    }


}