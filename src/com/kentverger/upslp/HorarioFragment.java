package com.kentverger.upslp;

import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class HorarioFragment extends SherlockFragment {
	private String dia;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.listviewcontent, container, false);
		String Json = getArguments().getString("json");
		Log.d("JSON", Json);
		dia = getArguments().getString("dia");
		try {
			JSONArray horario_json = new JSONArray(Json);
			
			ArrayList<ArrayList> lunes = new ArrayList<ArrayList>();
			ArrayList<ArrayList> martes = new ArrayList<ArrayList>();
			ArrayList<ArrayList> miercoles = new ArrayList<ArrayList>();
			ArrayList<ArrayList> jueves = new ArrayList<ArrayList>();
			ArrayList<ArrayList> viernes = new ArrayList<ArrayList>();
			ArrayList<ArrayList> sabado = new ArrayList<ArrayList>();
			
			
			
			
			for(int x = 0; x < horario_json.length(); x++){
				
				ArrayList<String> horario = new ArrayList<String>();
				
				JSONObject materia = horario_json.getJSONObject(x);
				
				if(!materia.getString("HORINILUN").equals("null")){
					horario.add(materia.getString("MATERIA"));
					horario.add(materia.getString("HORINILUN") + " a " + materia.getString("HORFINLUN"));
					horario.add("Aula: " + materia.getString("AULA"));
					lunes.add(horario);
				}if(!materia.getString("HORINIMAR").equals("null")){
					horario.add(materia.getString("MATERIA"));
					horario.add(materia.getString("HORINIMAR") + " a " + materia.getString("HORFINMAR"));
					horario.add("Aula: " + materia.getString("AULA"));
					martes.add(horario);
				}if(!materia.getString("HORINIMIE").equals("null")){
					horario.add(materia.getString("MATERIA"));
					horario.add(materia.getString("HORINIMIE") + " a " + materia.getString("HORFINMIE"));
					horario.add("Aula: " + materia.getString("AULA"));
					miercoles.add(horario);
				}if(!materia.getString("HORINIJUE").equals("null")){
					horario.add(materia.getString("MATERIA"));
					horario.add(materia.getString("HORINIJUE") + " a " + materia.getString("HORFINJUE"));
					horario.add("Aula: " + materia.getString("AULA"));
					jueves.add(horario);
				}if(!materia.getString("HORINIVIE").equals("null")){
					horario.add(materia.getString("MATERIA"));
					horario.add(materia.getString("HORINIVIE") + " a " + materia.getString("HORFINVIE"));
					horario.add("Aula: " + materia.getString("AULA"));
					viernes.add(horario);
				}if(!materia.getString("HORINISAB").equals("null")){
					horario.add(materia.getString("MATERIA"));
					horario.add(materia.getString("HORINISAB") + " a " + materia.getString("HORFINSAB"));
					horario.add("Aula: " + materia.getString("AULA"));
					sabado.add(horario);
				}
			}
			
			if(dia.equals("Lunes")){
				HorarioAdapter adapter = new HorarioAdapter(lunes);
				ListView lista = (ListView)v.findViewById(R.id.listView1);
				lista.setAdapter(adapter);
			}else if(dia.equals("Martes")){
				HorarioAdapter adapter = new HorarioAdapter(martes);
				ListView lista = (ListView)v.findViewById(R.id.listView1);
				lista.setAdapter(adapter);
			}else if(dia.equals("Miercoles")){
				HorarioAdapter adapter = new HorarioAdapter(miercoles);
				ListView lista = (ListView)v.findViewById(R.id.listView1);
				lista.setAdapter(adapter);
			}else if(dia.equals("Jueves")){
				HorarioAdapter adapter = new HorarioAdapter(jueves);
				ListView lista = (ListView)v.findViewById(R.id.listView1);
				lista.setAdapter(adapter);
			}else if(dia.equals("Viernes")){
				HorarioAdapter adapter = new HorarioAdapter(viernes);
				ListView lista = (ListView)v.findViewById(R.id.listView1);
				lista.setAdapter(adapter);
			}else if(dia.equals("Sabado")){
				HorarioAdapter adapter = new HorarioAdapter(sabado);
				ListView lista = (ListView)v.findViewById(R.id.listView1);
				lista.setAdapter(adapter);
			}
			
		} catch (JSONException e) {
			Log.d("Weird Shit Happening", e.getMessage());
		}

		
		return v;
	}
	public class HorarioAdapter extends BaseAdapter {

		private ArrayList data;

		public HorarioAdapter(ArrayList data) {
			super();
			this.data = data;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int arg0) {
			return data.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		@Override
		public View getView(int pos, View v, ViewGroup parent) {
			if(v == null){
				LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
				v = layoutInflater.inflate(R.layout.list_item_horario, null);
			}
			TextView materia = (TextView) v.findViewById(R.id.horario_materia);
			TextView hora = (TextView) v.findViewById(R.id.horario_hora);
			TextView aula = (TextView) v.findViewById(R.id.aula);
			
			//v.setBackgroundDrawable(getResources().getDrawable(R.drawable.gris));
			
			Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "BenchNine-Regular.ttf");
			materia.setTypeface(font);
			hora.setTypeface(font);
			aula.setTypeface(font);
			
			ArrayList materia_list = (ArrayList) data.get(pos);
			
			
			materia.setText((String) materia_list.get(0));
			hora.setText((String) materia_list.get(1));
			aula.setText((String) materia_list.get(2));
			
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			int horario = Integer.parseInt(((String) materia_list.get(1)).substring(0, 2));
			
			if(horario < hour){
				v.setBackgroundDrawable(getResources().getDrawable(R.drawable.gris));
			}else if(horario == hour){
				v.setBackgroundDrawable(getResources().getDrawable(R.drawable.amarillo));
			}else if(horario > hour){
				v.setBackgroundDrawable(getResources().getDrawable(R.drawable.verde));
			}
			
			return v;
		}


	}

}
