/*package com.ppa.perfildeaprendizado;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.data.model.Estilo;
import com.ppa.perfildeaprendizado.ui.detalhes.DetalhesEstilosAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class DetalhesEstilosActivity extends AppCompatActivity {
    public static String TAG = DetalhesEstilosActivity.class.getSimpleName();

    TextView mEstilosNaoCondizentes;
    RecyclerView mPredominantesRecyclerView;
    RecyclerView mNaoPredominantesRecyclerView;

    List<Estilo> mList = new ArrayList<>();

    DetalhesEstilosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEstilosNaoCondizentes = (TextView) findViewById(R.id.outros_estilos);
        mPredominantesRecyclerView = (RecyclerView) findViewById(R.id.lista_detalhes_predominantes);
        mNaoPredominantesRecyclerView = (RecyclerView) findViewById(R.id.lista_detalhes_outros);
        setContentView(R.layout.activity_detalhes_estilos);

        mPredominantesRecyclerView.setHasFixedSize(false);
        mPredominantesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPredominantesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNaoPredominantesRecyclerView.setHasFixedSize(false);
        mNaoPredominantesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mNaoPredominantesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*
        Comparator<Equipamento> equipamentoComparator = new Comparator<Equipamento>() {
            @Override
            public int compare(Equipamento equipamento, Equipamento equipamento1) {
                return equipamento.getStringValue(Equipamento.ACTDESCRICAO).compareTo(equipamento1.getStringValue(Equipamento.ACTDESCRICAO));
            }
        };*/
/*
        mList = new ArrayList<>();

//        mAdapter = new EquipamentoAdapter( this, searchFilter, false) {
//            @Override
//            public void onClickItemListener(View view, Equipamento entity) {
//                Intent intent = new Intent();
//                intent.putExtra(getString(R.string.equipamento_id), entity.getLongValue(Equipamento.ACTEQUIPAMENTOID));
//                setResult(200, intent);
//                finish();
//            }
//        };
//        mRecyclerView.setAdapter(mAdapter);

        carregarEquipamentos();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void carregarEstilos(Bundle savedInstanceState) {
        List<>savedInstanceState.get("EstilosPredominantes");
        mList.clear();
        mList.addAll(Equipamento.findAll());

        if (mList.size() < 1) {
            mRecyclerView.setVisibility(View.GONE);
            mSemEquipamento.setVisibility(View.VISIBLE);
        }

//        if(mAdapter != null) mAdapter.filter(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        addSearchMenu(menu,mAdapter,mRecyclerView, getMenuInflater());

        return super.onCreateOptionsMenu(menu);
    }

}
*/