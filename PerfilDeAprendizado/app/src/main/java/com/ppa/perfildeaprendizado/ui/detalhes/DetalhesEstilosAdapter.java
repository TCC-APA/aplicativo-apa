package com.ppa.perfildeaprendizado.ui.detalhes;
/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.activia.activiafs.bean.Equipamento;
import br.com.iclass.fs.mobile.and.R;
import br.com.iclass.fs.mobile.and.view.activity.InventariarAtivoActivity;
import br.com.iclass.fs.mobile.and.view.fragment.os.EquipamentoFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesEstilosAdapter extends RecyclerView.Adapter<DetalhesEstilosAdapter.DetalhesEstilosViewHolder> {

    private List<Equipamento> mDataset = new ArrayList<>();
    private EquipamentoFragment fragment;
    private Context context;

    public DetalhesEstilosAdapter(@NonNull Context context, @NonNull br.com.iclass.fs.mobile.and.view.activity.DetalhesEstilosActivity fragment, @NonNull List<Equipamento> mDataset){
        this.context = context;
        this.fragment = fragment;
        this.mDataset = mDataset;
        atualizarLista();
    }

    @Override
    public DetalhesEstilosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipamento_item, parent, false);
        return new DetalhesEstilosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetalhesEstilosViewHolder holder, final int position) {
        final Equipamento entity = this.mDataset.get(position);
        holder.mDescricao.setText(entity.getStringValue(Equipamento.ACTDESCRICAO));
        if(entity.getStringValue(Equipamento.ACTFABRICANTE)!= null && entity.getStringValue(Equipamento.ACTFABRICANTE).isEmpty())
            holder.mFabricante.setVisibility(View.GONE);
        holder.mFabricante.setText(entity.getStringValue(Equipamento.ACTFABRICANTE));

        if(entity.getStringValue(Equipamento.ACTMODELO)!= null && entity.getStringValue(Equipamento.ACTMODELO).isEmpty())
            holder.mModelo.setVisibility(View.GONE);
        holder.mModelo.setText(entity.getStringValue(Equipamento.ACTMODELO));

        if(entity.getStringValue(Equipamento.ACTCARGATERMICA)!= null && entity.getStringValue(Equipamento.ACTCARGATERMICA).isEmpty())
            holder.mCargaTermica.setVisibility(View.GONE);
        holder.mCargaTermica.setText(entity.getStringValue(Equipamento.ACTCARGATERMICA));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                fragment.editarEquipamento(entity.getId());
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selecionarEquipamento(position);
            }
        });

    }

    private void selecionarEquipamento(int position) {
        InventariarAtivoActivity activity = (InventariarAtivoActivity) context;
        activity.sendDataToFragment(this.mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset != null ? mDataset.size() : 0;
    }

    private void atualizarLista() {
        if (mDataset != null || !mDataset.isEmpty()) {
            notifyDataSetChanged();
        }
    }

    public void populateNextPage(List<Equipamento> mDataset){
        this.mDataset.addAll(mDataset);
        this.atualizarLista();
    }

    public void limparLista(){
        this.mDataset.clear();
    }

    public class DetalhesEstilosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.equipamento_item_descricao)
        TextView mDescricao;

        @BindView(R.id.equipamento_item_fabricante)
        TextView mFabricante;

        @BindView(R.id.equipamento_item_modelo)
        TextView mModelo;

        @BindView(R.id.equipamento_item_carga_termica)
        TextView mCargaTermica;


        public DetalhesEstilosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}*/
