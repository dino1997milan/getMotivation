package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.view.View.GONE;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
//

public class MyAdapterProvaSuoni extends RecyclerView.Adapter<MyAdapterProvaSuoni.MyViewHolderProvaSuoni> {

    ArrayList<DataModel> mDataModelList;
    Context mContext;
     //non usare mai più variabili statiche
    // dato che si deve fare in modo che vi sia il rilascio del suono quando si va in altri fragment o activity, o quando si premono i tre tasti di sistema, ho posto la variabile statica per dare accesso solo al fragment a questa variabile
    //(anche se ho posto statica la variabile l'ho importata solo nel fragment, in modo da poter gestire il release in onPause e bloccare la musica una volta usciti dal fragment)



    public MyAdapterProvaSuoni(ArrayList<DataModel> datamodellist, Context context){
        mDataModelList = datamodellist;
        mContext = context;
    }


    @NonNull
    @Override
    public MyViewHolderProvaSuoni onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recyclerview_item, parent, false);
        return new MyViewHolderProvaSuoni(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderProvaSuoni holder, int position) {
        holder.bind(mDataModelList.get(position), mContext);
    }

    @Override
    public int getItemCount() {
        return mDataModelList.size();
    }



    public class MyViewHolderProvaSuoni extends RecyclerView.ViewHolder{
        ImageView mImageView;
        MaterialTextView mMotivatorName;
        MaterialButton mButton;
        SwitchMaterial mSwitch;


        //release è un metodo di MediaPlayer utilizzato per deallocare le risorse utilizzate al termine dell'utilizzo
        //citazione google developers:
        //Rilascio di MediaPlayer:
        //"Un oggetto MediaPlayer può consumare preziose risorse di sistema. Pertanto, dovresti sempre prendere precauzioni extra per assicurarti di non aggrapparti a un'istanza più a lungo del necessario. Quando hai finito, dovresti sempre chiamare per assicurarti che tutte le risorse di sistema allocate ad esso siano rilasciate correttamente. Ad esempio, se si utilizza a e l'attività riceve una chiamata a , è necessario rilasciare il file , perché ha poco senso trattenerlo mentre l'attività non interagisce con l'utente (a meno che non si stiano riproducendo file multimediali in background, come discusso nella sezione successiva). Quando l'attività viene ripresa o riavviata, ovviamente, è necessario crearne una nuova e prepararla di nuovo prima di riprendere la riproduzione."



        public MyViewHolderProvaSuoni(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mMotivatorName = itemView.findViewById(R.id.nameTextView);
            mButton = itemView.findViewById(R.id.buttonProva);
            mSwitch = itemView.findViewById(R.id.switchVH);
            mButton.setVisibility(View.VISIBLE);
            mSwitch.setVisibility(GONE);


        }
        public void bind(DataModel dataModel, Context mContext) {
            mImageView.setImageDrawable(ContextCompat.getDrawable(mContext, dataModel.mMotivatorImageId));
            mMotivatorName.setText(dataModel.getmMotivatorName());

            mButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(mContext, ProvaSuoniActivity.class);
                                               intent.putExtra("VedoChièIlGiocatore", dataModel.posizioneNellaRecyclerView);
                                               mContext.startActivity(intent);
                                           }
                                       });
//                    PopupMenu popup = new PopupMenu(itemView.getContext(), mButton);
//                    popup.getMenuInflater().inflate(R.menu.menu_popup_sound, popup.getMenu());
//                    popup.setOnMenuItemClickListener(
//                            new PopupMenu.OnMenuItemClickListener() {
//                                @Override
//                                public boolean onMenuItemClick(MenuItem item) {
//                                    if (item.getItemId() == R.id.itemprimaoperazione) {
////                                        if (suoni == null) {
////                                            Toast.makeText(itemView.getContext(), "suono uno selezionato", Toast.LENGTH_SHORT).show();
////                                        }
//                                        //play(itemView, dataModel.mMotivatorSound1);
//                                    }
//
//                                    if (item.getItemId() == R.id.itemsecondaoperazione) {
////                                        if (suoni == null) {
////                                            Toast.makeText(itemView.getContext(), "suono due selezionato", Toast.LENGTH_SHORT).show();
////                                        }
//                                        //play(itemView, dataModel.mMotivatorSound2);
//                                    }
//                                    if (item.getItemId() == R.id.itemterzaoperazione) {
////                                        if(suoni==null){Toast.makeText(itemView.getContext(), "suono tre selezionato", Toast.LENGTH_SHORT).show();
////                                        }
//                                        //play(itemView, dataModel.mMotivatorSound3);
//                                    }
//                                    if (item.getItemId()==R.id.itemquartaoperazione) {
////                                        if(suoni==null){Toast.makeText(itemView.getContext(), "suono quattro selezionato", Toast.LENGTH_SHORT).show();
////                                        }
//                                        //play(itemView, dataModel.mMotivatorSound4);
//                                    }
//                                    if (item.getItemId()==R.id.itemquintaoperazione) {
////                                        if(suoni==null){Toast.makeText(itemView.getContext(), "suono cinque selezionato", Toast.LENGTH_SHORT).show();
////                                        }
//                                        //play(itemView, dataModel.mMotivatorSound5);
//                                    }
//                                            return false;
//                                }
//                            }
//                    );
//                    popup.show();
//                }
//            });
        }
    }



}


