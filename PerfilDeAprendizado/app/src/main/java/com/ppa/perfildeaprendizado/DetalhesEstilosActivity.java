package com.ppa.perfildeaprendizado;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ppa.perfildeaprendizado.controller.MenuController;
import com.ppa.perfildeaprendizado.data.DetalhesEstilosVO;
import com.ppa.perfildeaprendizado.data.model.Aluno;
import com.ppa.perfildeaprendizado.data.model.Estilo;
import com.ppa.perfildeaprendizado.ui.detalhes.DetalhesEstilosAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetalhesEstilosActivity extends AppCompatActivity {
    public static String TAG = DetalhesEstilosActivity.class.getSimpleName();

    @BindView(R.id.outros_estilos)
    TextView mEstilosNaoCondizentes;
    @BindView(R.id.lista_detalhes_predominantes)
    RecyclerView mPredominantesRecyclerView;
    @BindView(R.id.lista_detalhes_outros)
    RecyclerView mNaoPredominantesRecyclerView;
    private DetalhesEstilosVO detalhesEstilosVO;

    private List<Estilo> predList = new ArrayList<>();
    private List<Estilo> outrosList = new ArrayList<>();

    private DetalhesEstilosAdapter mAdapterPred;
    private DetalhesEstilosAdapter mAdapterOutros;

    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Setting Layout */
        setContentView(R.layout.activity_detalhes_estilos);

        /* Injection */
        ButterKnife.bind(this);
        detalhesEstilosVO = (DetalhesEstilosVO) getIntent().getSerializableExtra(DetalhesEstilosVO.class.getSimpleName());
        aluno = (Aluno) getIntent().getSerializableExtra(Aluno.class.getSimpleName());


        /*
        Comparator<Equipamento> equipamentoComparator = new Comparator<Equipamento>() {
            @Override
            public int compare(Equipamento equipamento, Equipamento equipamento1) {
                return equipamento.getStringValue(Equipamento.ACTDESCRICAO).compareTo(equipamento1.getStringValue(Equipamento.ACTDESCRICAO));
            }
        };*/

        predList = new ArrayList<>();
        outrosList = new ArrayList<>();
        carregarListas();
        carregarViews();


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

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void carregarListas() {
        if(detalhesEstilosVO != null){
            predList.clear();
            predList.addAll(detalhesEstilosVO.getEstilosPredominantes());
            if(detalhesEstilosVO.getEstilosNaoPredominantes() != null && !detalhesEstilosVO.getEstilosNaoPredominantes().isEmpty()){
                outrosList.clear();
                outrosList.addAll(detalhesEstilosVO.getEstilosNaoPredominantes());
            }
        }
    }

    private void carregarViews() {
        mAdapterPred = new DetalhesEstilosAdapter(this, predList, detalhesEstilosVO);
        mAdapterOutros = new DetalhesEstilosAdapter(this, outrosList, detalhesEstilosVO);

        mPredominantesRecyclerView.setHasFixedSize(false);
        mPredominantesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPredominantesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPredominantesRecyclerView.setAdapter(mAdapterPred);
        mPredominantesRecyclerView.setVisibility(View.VISIBLE);

        if(outrosList != null && !outrosList.isEmpty()){
            mNaoPredominantesRecyclerView.setHasFixedSize(false);
            mNaoPredominantesRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mNaoPredominantesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mNaoPredominantesRecyclerView.setVisibility(View.VISIBLE);
            mNaoPredominantesRecyclerView.setAdapter(mAdapterOutros);
            mEstilosNaoCondizentes.setVisibility(View.VISIBLE);
        } else {
            mEstilosNaoCondizentes.setVisibility(View.GONE);
            mNaoPredominantesRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuController.setupMenu(menu, getMenuInflater());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navigation_editar_perfil:
                MenuController.editarPerfilAction(this, aluno);
                return true;
            case R.id.navigation_inicio:
                MenuController.inicioAction(this, aluno);
                return true;
            case R.id.navigation_sobre:
                MenuController.sobreAction(this);
                return true;
            case R.id.navigation_sair:
                MenuController.sairAction(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
