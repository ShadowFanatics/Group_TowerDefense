package group.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sqlite.RankData;
import sqlite.postDataDAO;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RankActivity extends Activity{
	 	private ListView lv;
	 	private RelativeLayout view;
	    private List<RankData> mListlist;
	    private Button button;
		private int display_width, display_height;
		private long index = 0;
		private static final String TAG = "Rank_activity";
	    /** Called when the activity is first created. */  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.record);
	        lv = (ListView)findViewById(R.id.pay_record_listview);
	        view = (RelativeLayout) findViewById(R.id.mm_pay_layout);
	        button = (Button)findViewById(R.id.mm_back);
	        view.removeView((View)button);
	        button.setOnClickListener(backButtonListener);
	        
	        initData();
	        //addData();
	        //list sort
	        Collections.sort(mListlist,new Comparator<RankData>() {
	        	

	        	public int compare(RankData lhs, RankData rhs) {
					// TODO Auto-generated method stub
					//Date date1 = stringToDate(lhs.getTSec());
					//Date date2 = stringToDate(rhs.getTSec());
					int n1 = Integer.parseInt(lhs.getScore());
					int n2 = Integer.parseInt(rhs.getScore());
					//由大排到小
					if(n1 < n2){
						return 1;
					}
					return -1;
				}
			});
	        /*setIcon
	        Iterator<RankData> it = mListlist.listIterator();
	        int i = 1;//這變數要重寫
	        int j = 0;
	        while (it.hasNext()) {
	        	RankData rankData = it.next(); 
	        	if(j == 0 && i <= 3){
	        		rankData.setIcon1(getResources().getDrawable(cup[i-1]));
	        		rankData.setIcon2(getResources().getDrawable(images[i]));
	        	}
	        	else {
	        		rankData.setIcon1(getResources().getDrawable(images[j]));
	        		rankData.setIcon2(getResources().getDrawable(images[i]));
				}
				i++;
				if(i >= 10){
					j++;
					i = i / 10;
				}
			}*/
				
	        //setAdapter
	        MyAdapter myAdapter = new MyAdapter(mListlist);
	        lv.setAdapter(myAdapter);        
	        //lv.setOnItemClickListener(onItemClickListener);
	        
	        //this.setContentView(lv);
	          
	    }
	    
	    private OnItemClickListener onItemClickListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(RankActivity.this, "jijij", Toast.LENGTH_SHORT).show();
			}
		};
		
	    private void initData() {

	        mListlist = new ArrayList<RankData>();

			mListlist.add(new RankData(1,"30","00:30","123", "硬硬"));
			mListlist.add(new RankData(2,"25","01:25","321","邦弟"));
			mListlist.add(new RankData(3,"37","02:37","456","岳霖"));
			mListlist.add(new RankData(4,"7","03:67","654","熊貓"));
			mListlist.add(new RankData(5,"11","04:18","145","魚蛋"));
			mListlist.add(new RankData(6,"56","04:34","600","肉餅臉"));
			
			readState();

			DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			display_width = displayMetrics.widthPixels;
			display_height = displayMetrics.heightPixels;
		}
	    
		private Button.OnClickListener backButtonListener = new Button.OnClickListener()
		{		
			public void onClick(View v)
			{			
				Intent intent = new Intent();
				intent.setClass(RankActivity.this, MainActivity.class);
				startActivity(intent);
				RankActivity.this.finish();
			}
		};
	    
	    private void addData() {
			Bundle bundle = getIntent().getExtras();
			if ( bundle.getBoolean("save")) {
				String score = bundle.getString("score");
				String time = bundle.getString("time");
				String name = bundle.getString("name");
				String money = bundle.getString("money");
				RankData newData = new RankData(index,score, time, money, name);
				mListlist.add(newData);
				postDataDAO localData = new postDataDAO(getApplicationContext());
				localData.insert(newData);
				localData.close();
				index++;
			}
		}
	    
		private void readState() {
			postDataDAO localData = new postDataDAO(getApplicationContext());
			List<RankData> messages = localData.getAll();
			index = messages.size();
			for (int i = 0; i < messages.size(); i++) {
				Log.e("!!",String.valueOf(i));
				long id =  messages.get(i).id;
				String name =  messages.get(i).getName();
				String time =  messages.get(i).getTime();
				String score =  messages.get(i).getScore();
				String money = messages.get(i).getMoney();
				RankData item = new RankData(id, score, time, money, name);
				mListlist.add(item);
			}
			localData.close();
		}
	    
	    /*
	    public static Date stringToDate(String rankString) {
			ParsePosition position = new ParsePosition(0);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date dateValue = simpleDateFormat.parse(rankString, position);
	    	return dateValue;
		}*/
		
	    public class MyAdapter extends BaseAdapter {  
	    	  
	        private List<RankData> mList;
	        private LayoutInflater mInflater;
	      
	        public MyAdapter(List<RankData> list) {
				if (list == null) {
					list = new ArrayList<RankData>();
				}
				this.mList = list;
				this.mInflater = LayoutInflater.from(RankActivity.this);
	        }  
	      
	        public int getCount() {  
	            return mList != null ? mList.size() : 0;  
	        }  
	      
	        public Object getItem(int position) {  
	            return mList.get(position);  
	        }  
	      
	        public long getItemId(int position) {  
	            return position;  
	        }  
	          
	        private class ViewHolder {  
	            private TextView t1;  
	            private TextView t2;
	            private TextView t3;
	            private TextView t4;
	            //private ImageView imageview1;
	            //private ImageView imageview2;
	        }

			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ViewHolder mViewHolder;
				if (null == convertView) {
					mViewHolder = new ViewHolder();
					convertView = mInflater.inflate(R.layout.record_listview_item, null);
					mViewHolder.t1 = (TextView) convertView.findViewById(R.id.record_name);
					mViewHolder.t2 = (TextView) convertView.findViewById(R.id.record_time);
					mViewHolder.t3 = (TextView) convertView.findViewById(R.id.record_wave);
					mViewHolder.t4 = (TextView) convertView.findViewById(R.id.record_money);
					
					//mViewHolder.status = (ImageView) convertView.findViewById(R.id.record_statu);
					convertView.setTag(mViewHolder);
				} else {
					mViewHolder = (ViewHolder) convertView.getTag();
				}
	      
	            //holder.imageview1.setImageDrawable(mList.get(position).getIcon1());
	            //holder.imageview2.setImageDrawable(mList.get(position).getIcon2());
	            mViewHolder.t1.setText(mList.get(position).getName());
				mViewHolder.t2.setText(mList.get(position).getTime());
	            mViewHolder.t3.setText(mList.get(position).getScore() + "wave");
	            mViewHolder.t4.setText(mList.get(position).getMoney());
	            
	            //holder.imageview1.getLayoutParams().height = display_height / 9;
	            //holder.imageview1.getLayoutParams().width = display_width / 9;
	            //holder.imageview2.getLayoutParams().height = display_height / 9;
	            //holder.imageview2.getLayoutParams().width = display_width / 9;
	            /*holder.button.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
		                Toast.makeText(Ranking.this, "當前選中列表項為:sdfsdf", 
		                        Toast.LENGTH_SHORT).show();
		                Ranking.this.finish();
					}
				});*/
	            
				convertView.findViewById(R.id.reocord_item).setPadding(10, 0, 10, 0);
	            return convertView;  
			}
			
			
		    /*private Bitmap scaleBitmap(Bitmap bitmap){
		    		
		    	int width = bitmap.getWidth();
		    	int height = bitmap.getHeight();
		    	int new_width = display_width / 4 - 30;
		    	int new_height = display_height / 5;
		    	float scaleWidth = ((float) new_width ) / width;
		    	float scaleHeight = ((float) new_height ) / height;
		    	Matrix matrix = new Matrix();
		    	matrix.postScale(scaleWidth, scaleHeight);
		    	Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
		    	return newbmp;
		    };
		    
		    private Bitmap drawableToBitmap(Drawable drawable) {   
		        int w = drawable.getIntrinsicWidth();  
		        int h = drawable.getIntrinsicHeight();  
		   
		        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888  
		                : Bitmap.Config.RGB_565;  
		        Bitmap bitmap = Bitmap.createBitmap(w, h, config);  
		        Canvas canvas = new Canvas(bitmap);  
		        drawable.setBounds(0, 0, w, h);   
		        drawable.draw(canvas);
		        
		        return bitmap;  
		    */
	    }

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if(keyCode == KeyEvent.KEYCODE_BACK){
				Intent intent = new Intent();
				intent.setClass(RankActivity.this, MainActivity.class);
				startActivity(intent);;
				RankActivity.this.finish();
			}
			return super.onKeyDown(keyCode, event);
		}


}

