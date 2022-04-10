package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.viewpage2.photo;
import com.example.myapplication.viewpage2.photoAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frament_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frament_home extends Fragment {


    ImageView btn_tkb, btn_history, btn_callprofile;

    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frament_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frament_home.
     */
    // TODO: Rename and change types and number of parameters
    public static frament_home newInstance(String param1, String param2) {
        frament_home fragment = new frament_home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frament_home, container, false);


//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new Readdata().execute("https://vnexpress.net/rss/giao-duc.rss");
//            }
//        });


        btn_tkb =(ImageView) view.findViewById(R.id.btn_callform_tkb);
        btn_history = (ImageView) view.findViewById(R.id.btn_callform_history);
        btn_callprofile = (ImageView) view.findViewById(R.id.btn_callform_profile);

        viewPager2 = (ViewPager2) view.findViewById(R.id.viewpager2_loading_img);
        circleIndicator3 = (CircleIndicator3) view.findViewById(R.id.circle_viewpage);


        photoAdapter photoAdapter1 = new photoAdapter((FragmentActivity) getContext(), getListPhoto());
        viewPager2.setAdapter(photoAdapter1);
        circleIndicator3.setViewPager(viewPager2);



        btn_tkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.role.equals("1")){
                        Intent intent1 = new Intent(getContext(), weekly_calendar.class);
                        startActivity(intent1);
                } else if (MainActivity.role.equals("2")) {
                    Intent intent2 = new Intent(getContext(), weekly_calendar.class);
                    startActivity(intent2);
                }

            }
        });

        btn_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.role.equals("1")){
                    Intent intent = new Intent(getContext(), History_attendance.class);
                    //Intent intent = new Intent(getContext(), Lichsudiemdanh_SV.class);
                    startActivity(intent);
                }else {

                    Intent intent = new Intent(getContext(), Lichsudiemdanh_SV.class);
                    startActivity(intent);
                }

            }
        });

        btn_callprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Call_profile.class);
                startActivity(intent);
            }
        });




        return view;
    }


    //code đọc tin tức chưa hoàn thành
//    private void runOnUiThread(Runnable runnable) {
//    }
//
//
//    class Readdata extends AsyncTask<String, Integer, String>{
//
//        @Override
//        protected String doInBackground(String... params) {
//            return docnoidung_URL(params[1]);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Xml_dompares parser = new Xml_dompares();
//            Document document = (Document) parser.getDocumented(s);
//            NodeList nodeList = document.getElementsByTagName("item");
//            NodeList nodeList_noidung = document.getElementsByTagName("description");
//            String hinhanh = "";
//            String title = "";
//            String link ="";
//            for(int i=0; i<nodeList.getLength(); i++){
//                String cdata = nodeList_noidung.item(i+1).getTextContent();
//                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
//                Matcher matcher = p.matcher(cdata);
//                if (matcher.find()){
//                    hinhanh = matcher.group(1);
//                    //Log.d("hinhanh",hinhanh +"................."+i);
//                }
//                Element element = (Element) nodeList.item(i);
//                title +=parser.getValue(element,"title");
//                link = parser.getValue(element,"link");
//                //Log.d("link",link);
//            }
//            super.onPostExecute(s);
//
//        }
//    }

//    private static String docnoidung_URL(String theUrl){
//        StringBuilder content = new StringBuilder();
//        try{
//            URL url = new URL(theUrl);
//
//            URLConnection urlConnection = url.openConnection();
//
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line;
//
//            while ((line = bufferedReader.readLine()) != null){
//                content.append(line + "\n");
//            }
//            bufferedReader.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return content.toString();
//    }





    //list loanding img
    private List<photo> getListPhoto(){
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.hutech1));
        list.add(new photo(R.drawable.hutech2));
        list.add(new photo(R.drawable.hutech3));
        list.add(new photo(R.drawable.hutech4));

        return list;
    }
}