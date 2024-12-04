package dm2e.davidclarkson.basededatos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RegistroAdapter extends RecyclerView.Adapter<RegistroAdapter.ViewHolder> {

    private final ArrayList<Registro> registros;

    public RegistroAdapter(ArrayList<Registro> registros) {
        this.registros = registros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Registro registro = registros.get(position);
        holder.tvId.setText("ID: " + registro.getId());
        holder.tvName.setText("Nombre: " + registro.getName());
        holder.tvEmail.setText("Correo: " + registro.getEmail());
    }

    @Override
    public int getItemCount() {
        return registros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvEmail = itemView.findViewById(R.id.tv_email);
        }
    }
}
