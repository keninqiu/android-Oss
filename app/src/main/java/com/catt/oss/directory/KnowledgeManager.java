package com.catt.oss.directory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.catt.oss.R;
import com.catt.oss.adapter.ListViewPageAdapter;
import com.catt.oss.common.app.BaseActivity;
import com.catt.oss.common.app.PublicPage;
import com.catt.oss.common.data.Config;

import java.util.ArrayList;
import java.util.List;
public class KnowledgeManager extends PublicPage implements OnClickListener {
    public Button selectAll;
    private int selectnum=0;
    private Button btn_add;
    private Button btn_delete;
    private Button btn_modify;
    String result;
    private TextView tvtitle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Intent intent=getIntent();
    		result=intent.getStringExtra(Config.TITLE);
    		if(result.equals(Config.DIRECTORY)){
    			fa=new ListViewPageAdapter(this, createData(),createData2(),createChecked());
                listview.setAdapter(fa);
    		}
    		if(result.equals(Config.KNOWLEDGE)){
    			fa=new ListViewPageAdapter(this, createknowledgeData(),createData2(),createChecked());
                listview.setAdapter(fa);
    		}
            
           
    } 
    
 // �����Ѿ�ѡ�е�������ʼ����
	public static List<String> createChecked() {
		List<String> datas = new ArrayList<String>();
		return datas;
	}

	// �������.Ҫչ�ֵ�
	public static List<String> createData() {
		List<String> datas = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			datas.add("000_" + i);
		}
		return datas;
	}
	public static List<String> createknowledgeData() {
		List<String> datas = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			datas.add("111_" + i);
		}
		return datas;
	}
	// �������.Ҫչ�ֵ�
	public static List<String> createData2() {
		List<String> datas = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			datas.add("2011-9-6");
		}
		return datas;
	}
    
	@Override
	public void onClick(View view) {
		View myview=null;
		switch(view.getId()){
		  //Ŀ¼���
		case R.id.add:
			Intent intent =new Intent();
			intent.putExtra(Config.DIRECTORY,Config.ADD);
			if(result.equals(Config.DIRECTORY)){
				intent.setClass(this, DirectoryAddOrModify.class);
			}else if(result.equals(Config.KNOWLEDGE)){
				intent.setClass(this, KnowledgeAddOrModify.class);
			}
			startActivity(intent);
			break;
			//����޸�
		case R.id.modify:
			if(fa.checkedNumbers()==1){
				Intent intentwo =new Intent();
				ArrayList<String> listdata=new ArrayList<String>();
				listdata.add("hello world");
				listdata.add("study");
				listdata.add("wjf");
				listdata.add("number");
				listdata.add("good");
				listdata.add("do you love life");
				Bundle bundle=new Bundle();
				bundle.putStringArrayList(Config.UPDATE, listdata);
				intentwo.putExtra(Config.DIRECTORY,Config.MODIFY);
				intentwo.putExtras(bundle);
				if(result.equals(Config.DIRECTORY)){
					intentwo.setClass(this, DirectoryAddOrModify.class);
				}else if(result.equals(Config.KNOWLEDGE)){
					intentwo.setClass(this, KnowledgeAddOrModify.class);
				}
				startActivity(intentwo);	
			}else if(fa.checkedNumbers()>1){
				Toast.makeText(this, "222", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(this, "333", Toast.LENGTH_LONG).show();
			}
			
			break;
			//���ɾ��
		case R.id.delete:
			if(fa.checkedNumbers()>0){
				fa.deleteData();
				if(fa.dataNumbers()<=0){
					Toast.makeText(this, "444", Toast.LENGTH_LONG).show();
				}
			}else{
				Toast.makeText(this, "555", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.selectAll:
			selectnum++;
			if(selectnum%2==1){
				fa.setAllItemsChcekd(true);
			}else{
				fa.setAllItemsChcekd(false);
			}
			break;
		}
	}
	//�õ��ؼ��ľ��
	@Override
	protected void initialComponent(BaseActivity context) {
		super.initialComponent(context);
		selectAll=(Button) this.findViewById(R.id.selectAll);
		btn_add=(Button) this.findViewById(R.id.add);
		btn_delete=(Button) this.findViewById(R.id.delete);
		btn_modify=(Button) this.findViewById(R.id.modify);
		tvtitle=(TextView) this.findViewById(R.id.directorymanager);
		if(result.equals(Config.KNOWLEDGE)){
			tvtitle.setText("֪ʶ");
		}
	}

	@Override
	protected void initialSetup(BaseActivity context) {
		super.initialSetup(context);
		
	}
    //�¼��ļ���
	@Override
	protected void setComponentListener(BaseActivity context) {
		super.setComponentListener(context);
		selectAll.setOnClickListener(this);
		btn_add.setOnClickListener(this);
		btn_delete.setOnClickListener(this);
		btn_modify.setOnClickListener(this);
	}
	
}