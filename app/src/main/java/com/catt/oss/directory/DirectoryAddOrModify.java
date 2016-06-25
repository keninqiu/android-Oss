package com.catt.oss.directory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.catt.oss.R;
import com.catt.oss.common.app.BaseActivity;
import com.catt.oss.common.data.Config;
import com.catt.oss.common.view.TextViewEdit;

import java.util.ArrayList;

public class DirectoryAddOrModify extends BaseActivity {
    private LinearLayout linearlayout_createunit;
    private LinearLayout linearlayout_createdept;
    private LinearLayout linearlayout_createperson;
    private LinearLayout linearlayout_muluname;
    private LinearLayout linearlayout_mulutype;
    private LinearLayout linearlayout_remarkinfo;
    private DisplayMetrics dm;
    private Button submit;
    private Button cancel;
    private TextView tvtitle;
    String addOrmodify;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.directory_addormodify);
		
	}
	@Override
	protected void initialComponent(BaseActivity context) {
		super.initialComponent(context);
		linearlayout_createunit=(LinearLayout) this.findViewById(R.id.linearlayout_createunit);
		linearlayout_createdept=(LinearLayout) this.findViewById(R.id.linearlayout_create_dept);
		linearlayout_createperson=(LinearLayout) this.findViewById(R.id.linearlayout_create_person);
		linearlayout_muluname=(LinearLayout) this.findViewById(R.id.linearlayout_muluname);
		linearlayout_mulutype=(LinearLayout) this.findViewById(R.id.linearlayout_mulntype);
		linearlayout_remarkinfo=(LinearLayout) this.findViewById(R.id.linearlayout_remarkinfo);
		submit=(Button) this.findViewById(R.id.btn_submit);
		cancel=(Button) this.findViewById(R.id.btn_cancel);
		tvtitle=(TextView) this.findViewById(R.id.directory_addormodify);
	}
	@Override
	protected void initialSetup(BaseActivity context) {
		super.initialSetup(context);
		dm=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int textwidth=dm.widthPixels/3;
		int textheight=dm.heightPixels/6;
		int editwidth=dm.widthPixels/2;
		int editheight=dm.heightPixels/6;
		Intent intent=getIntent();
		addOrmodify=intent.getStringExtra(Config.DIRECTORY);
		TextViewEdit view1 = null;
		TextViewEdit view2=null;
		TextViewEdit view3=null;
		TextViewEdit view4=null;
		TextViewEdit view5=null;
		TextViewEdit view6=null;
		if(addOrmodify.equals(Config.ADD)){
			 view1=new TextViewEdit(this, "111","",dm);
		     view2=new TextViewEdit(this, "222","",dm);
		     view3=new TextViewEdit(this, "333","",dm);
		     view4=new TextViewEdit(this, "444","",dm);
		     view5=new TextViewEdit(this, "555","",dm);
		     view6=new TextViewEdit(this, "666","",textwidth,textheight,editwidth,editheight);
		}if(addOrmodify.equals(Config.MODIFY)){
			tvtitle.setText(getResources().getText(R.string.modify));
			ArrayList<String>listdata=new ArrayList<String>();
			listdata=intent.getStringArrayListExtra("update");
			view1=new TextViewEdit(this, "777",listdata.get(0),dm);
		    view2=new TextViewEdit(this, "888",listdata.get(1),dm);
		    view3=new TextViewEdit(this, "999",listdata.get(2),dm);
		    view4=new TextViewEdit(this, "000",listdata.get(3),dm);
		    view5=new TextViewEdit(this, "111",listdata.get(4),dm);
		    view6=new TextViewEdit(this, "222",listdata.get(5),textwidth,textheight,editwidth,editheight);
		}
		 linearlayout_createunit.addView(view1);
	     linearlayout_createdept.addView(view2);
	     linearlayout_createperson.addView(view3);
	     linearlayout_muluname.addView(view4);
	     linearlayout_mulutype.addView(view5);
	     linearlayout_remarkinfo.addView(view6);
	    
	    
	}
	@Override
	protected void setComponentListener(final BaseActivity context) {
		super.setComponentListener(context);
			submit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(addOrmodify.equals("add")){
						dialogShow(DirectoryAddOrModify.this,"333","444");
					}else if(addOrmodify.equals("modify")){
						dialogShow(DirectoryAddOrModify.this,"555","666");
					}else{
						Toast.makeText(context, "777", Toast.LENGTH_LONG).show();
					}
					
				}
			});
			cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					
				}
			});
		}
	public void dialogShow(final Context context,String title,String message){
		new AlertDialog.Builder(context).setTitle(title).setMessage(message)
		.setIcon(R.drawable.icon).setPositiveButton("888", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(context, "999", Toast.LENGTH_LONG).show();
				    
			}
		}).setNegativeButton("000", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).create().show();
	}
	

}
