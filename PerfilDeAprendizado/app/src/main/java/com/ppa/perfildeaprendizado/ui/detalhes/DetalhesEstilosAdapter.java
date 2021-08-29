package com.ppa.perfildeaprendizado.ui.detalhes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ppa.perfildeaprendizado.DetalhesEstilosActivity;
import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.data.DetalhesEstilosVO;
import com.ppa.perfildeaprendizado.data.model.Estilo;
import com.ppa.perfildeaprendizado.data.model.RangePontuacaoClassificacao;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalhesEstilosAdapter extends RecyclerView.Adapter<DetalhesEstilosAdapter.DetalhesEstilosViewHolder> {

    private final DetalhesEstilosVO detalhesEstilosVO;
    private final DetalhesEstilosActivity fragment;
    private final List<Estilo> mDataset;

    public DetalhesEstilosAdapter(@NonNull DetalhesEstilosActivity fragment, @NonNull List<Estilo> mDataset, @NonNull DetalhesEstilosVO detalhesEstilosVO){
        this.fragment = fragment;
        this.mDataset = mDataset;
        this.detalhesEstilosVO = detalhesEstilosVO;
        atualizarLista();
    }

    @Override
    public DetalhesEstilosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detalhe_item, parent, false);
        return new DetalhesEstilosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetalhesEstilosViewHolder holder, final int position) {
        final Estilo entity = this.mDataset.get(position);
        Map<Long, RangePontuacaoClassificacao> mapRange = detalhesEstilosVO.getIdEstiloRange();
        StringBuilder strRange = new StringBuilder();
        if(mapRange != null && mapRange.containsKey(entity.getId())){
            RangePontuacaoClassificacao range = mapRange.get(entity.getId());
            strRange.append(": ")
                    .append(range.getMinValue())
                    .append(" ... ")
                    .append(range.getMaxValue())
                    .append(" (")
                    .append(range.getClassificacao())
                    .append(")");
        }
        holder.mEstilo.setText(entity.getNome() + strRange.toString());
        holder.mCaracteristicas.setText(entity.getCaracteristicas());
        if (detalhesEstilosVO.getEstilosPredominantes() != null && !detalhesEstilosVO.getEstilosNaoPredominantes().isEmpty() && detalhesEstilosVO.getEstilosPredominantes().contains(entity)){
            holder.mComoMelhorar.setText(R.string.como_melhorar_predominante);
            holder.mSugestao.setText(entity.getSugestoes());
        } else {
            holder.mComoMelhorar.setText(R.string.como_melhorar_nao_predominante);
            holder.mSugestao.setText(entity.getSugestoes()/*.getComoMelhorar()*/);
        }
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

    public class DetalhesEstilosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_estilo)
        TextView mEstilo;

        @BindView(R.id.caracteristicas_estilo)
        TextView mCaracteristicas;

        @BindView(R.id.como_melhorar)
        TextView mComoMelhorar;

        @BindView(R.id.sugestoes_estilo)
        TextView mSugestao;


        public DetalhesEstilosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
