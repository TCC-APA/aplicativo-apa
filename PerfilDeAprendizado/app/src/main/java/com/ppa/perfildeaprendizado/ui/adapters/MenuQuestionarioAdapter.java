package com.ppa.perfildeaprendizado.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppa.perfildeaprendizado.MenuQuestionariosActivity;
import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.data.model.PerfilAluno;
import com.ppa.perfildeaprendizado.data.model.Questionario;
import com.ppa.perfildeaprendizado.task.BuscarPerfilAlunoTask;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuQuestionarioAdapter extends RecyclerView.Adapter<MenuQuestionarioAdapter.MenuQuestionarioViewHolder> {

    private List<Questionario> mDataset;
    private String matriculaAluno;
    private MenuQuestionariosActivity activity;

    public MenuQuestionarioAdapter(@NonNull List<Questionario> mDataset, @NonNull String matriculaAluno, MenuQuestionariosActivity activity) {
        this.mDataset = mDataset;
        this.matriculaAluno = matriculaAluno;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MenuQuestionarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.questionario_item, parent, false);
        return new MenuQuestionarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuQuestionarioViewHolder holder, int position) {
        final Questionario entity = this.mDataset.get(position);

        holder.tituloQuestionario.setText(entity.getNome());
        holder.botaoResponderNovamente.setVisibility(View.GONE);
        holder.imagemStatusVerde.setVisibility(View.GONE);
        holder.imagemStatusCinza.setVisibility(View.GONE);

        try {
            PerfilAluno perfilAluno = new BuscarPerfilAlunoTask(matriculaAluno, entity.getId()).execute().get();
            if(entity.getSobre() != null && !entity.getSobre().isEmpty()){
                holder.botaoSobre.setVisibility(View.VISIBLE);
                holder.botaoSobre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.goToSobre(entity);
                    }
                });
            } else {
                holder.botaoSobre.setVisibility(View.INVISIBLE);
            }

            if(perfilAluno != null){
                holder.botaoResponderNovamente.setVisibility(View.VISIBLE);
                holder.botaoResponderNovamente.setText("Responder Novamente");
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String dataRespondido = formato.format(perfilAluno.getDataRealizado());
                holder.statusQuestionario.setText("Respondido em: " + dataRespondido);
                holder.imagemStatusVerde.setVisibility(View.VISIBLE);
                holder.imagemStatusCinza.setVisibility(View.GONE);
                holder.botaoVerResultado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.goToResultado(entity);
                    }
                });
                holder.botaoResponderNovamente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.goToQuestionario(entity);
                    }
                });
            }else{
                holder.botaoVerResultado.setVisibility(View.GONE);
                holder.botaoResponderNovamente.setVisibility(View.VISIBLE);
                holder.botaoResponderNovamente.setText("Responder este Questionário");
                holder.statusQuestionario.setText("Não respondido");
                holder.imagemStatusCinza.setVisibility(View.VISIBLE);
                holder.imagemStatusVerde.setVisibility(View.GONE);
                holder.botaoResponderNovamente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.goToQuestionario(entity);
                    }
                });
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public class MenuQuestionarioViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tituloquestionario)
        TextView tituloQuestionario;

        @BindView(R.id.statusquestionario)
        TextView statusQuestionario;

        @BindView(R.id.imagestatusverde)
        ImageView imagemStatusVerde;

        @BindView(R.id.imagestatuscinza)
        ImageView imagemStatusCinza;

        @BindView(R.id.respnovamente)
        Button botaoResponderNovamente;

        @BindView(R.id.verresultado)
        Button botaoVerResultado;

        @BindView(R.id.button_sobre)
        Button botaoSobre;



        public MenuQuestionarioViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
