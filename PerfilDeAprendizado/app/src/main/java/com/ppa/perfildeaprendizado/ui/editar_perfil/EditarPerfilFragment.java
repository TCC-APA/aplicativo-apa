package com.ppa.perfildeaprendizado.ui.editar_perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ppa.perfildeaprendizado.R;
import com.ppa.perfildeaprendizado.data.model.Aluno;

public class EditarPerfilFragment extends Fragment {

    private EditarPerfilViewModel editarPerfilViewModel;
    private EditText nome;
    private EditText matricula;
    private EditText idade;
    private EditText senha;
    private EditText confirmarSenha;
    private Spinner genero;
    private LinearLayout form;
    private Button enviar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        editarPerfilViewModel =
                ViewModelProviders.of(this).get(EditarPerfilViewModel.class);
        Aluno aluno = (Aluno) getActivity().getIntent().getSerializableExtra(Aluno.class.getSimpleName());
        View root = inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        form = root.findViewById(R.id.form);
        nome = root.findViewById(R.id.nome);
        matricula = root.findViewById(R.id.matricula);
        idade = root.findViewById(R.id.idade);
        senha = root.findViewById(R.id.senha);
        confirmarSenha = root.findViewById(R.id.confirmarSenha);
        genero = root.findViewById(R.id.genero);
        enviar = root.findViewById(R.id.enviar);

        nome.setText(aluno.getNome());
        matricula.setText(aluno.getMatricula());
        idade.setText(aluno.getIdade());
        matricula.setEnabled(false);

//        editarPerfilViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
