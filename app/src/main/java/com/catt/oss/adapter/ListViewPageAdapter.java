package com.catt.oss.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.catt.oss.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ListViewPageAdapter extends BaseAdapter {
	private List<String> datas = new ArrayList<String>(); // ������ʾ��
	private List<String> datastime = new ArrayList<String>(); // ������ʾ��ʱ��
	private List<String> checkeds = new ArrayList<String>(); // ��������Ѿ�ѡ���Ԫ��
	private Context context;
	
	private Map<String,Holder> holders = new HashMap<String,Holder>(); //���ĵؽ�����holder��������

	public ListViewPageAdapter(Context context, List<String> datas,List<String>datastime,
			List<String> chckedDatas) {
		super();
		this.context = context;
		this.datas = datas;
		this.datastime = datastime;
		this.checkeds = chckedDatas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int arg0) {
		return datas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	private class Holder {
		private CheckBox cb;
		private TextView infotitle;
		private TextView infotime;

		public CheckBox getCb() {
			return cb;
		}

		public void setCb(CheckBox cb) {
			this.cb = cb;
		}

		public TextView getInfotitle() {
			return infotitle;
		}

		public void setInfotitle(TextView infotitle) {
			this.infotitle = infotitle;
		}

		public TextView getInfotime() {
			return infotime;
		}

		public void setInfotime(TextView infotime) {
			this.infotime = infotime;
		}
		

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.common, null);
			holder = new Holder();
			CheckBox cb = (CheckBox) convertView.findViewById(R.id.cbox);
			holder.setCb(cb);
			TextView tv = (TextView) convertView.findViewById(R.id.infotitle);
			holder.setInfotitle(tv);
			TextView tvtime = (TextView) convertView.findViewById(R.id.infotime);
			holder.setInfotime(tvtime);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		String txt = datas.get(position);
		String time=datastime.get(position);
		
		if(!holders.containsKey(txt)) {
			holders.put(txt, holder);
		}
		
		System.out.println(position + ":" + txt);
		holder.getInfotitle().setText(txt);
		holder.getInfotime().setText(time);
		if (checkeds.contains(txt)) { // ����Ǳ�ѡ�еĻ�,��ʼ����
			holder.getCb().setChecked(true);
		} else {
			holder.getCb().setChecked(false); // ��סû��ѡ�еĻ�,Ҫ����ѡ��
		}
		holder.getCb().setOnCheckedChangeListener(
				new OrderBtnOnclickListener(holder, txt));
		return convertView;
	}

	private class OrderBtnOnclickListener implements OnCheckedChangeListener {
		private Holder holder = null;
		private String item = null;

		public OrderBtnOnclickListener(Holder holder, String item) {
			super();
			this.holder = holder;
			this.item = item;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (holder.getInfotitle().getText().toString().equals(item)) { //�����Ŀ�����
				if (isChecked) { // ���ѡ��
					// ����
					if (!checkeds.contains(item)) { 
						checkeds.add(item);
					}
				}//������ɾ�� 
				else {
					if (checkeds.contains(item)) { 
						checkeds.remove(item);
					}
				}
				System.out.println(checkeds);  
			}
		}
	}

	public void setAllItemsChcekd(boolean flag) {
		checkeds.clear();
		if(flag)
			checkeds.addAll(datas);
		/*for(String key : holders.keySet()) {
			Holder h = holders.get(key);
			h.getCb().setChecked(flag);
		}*/
		notifyDataSetChanged();
	}
	
	public String getCheckItem() {
		return checkeds.toString();
	}
	public int checkedNumbers(){
		return checkeds.size();
	}
	public int dataNumbers(){
		return datas.size();
	}
	public void deleteData(){
		int size=checkeds.size();
		for(int i=0;i<size;i++){
			int datasize=datas.size();
			for(int j=0;j<datasize;j++){
				if(checkeds.get(i).equals(datas.get(j))){
					datas.remove(j);
					break;
				}
			}
		}
		checkeds.clear();
		notifyDataSetChanged();
		
	}
}
	
