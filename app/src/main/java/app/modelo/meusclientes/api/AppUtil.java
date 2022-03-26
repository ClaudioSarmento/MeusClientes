package app.modelo.meusclientes.api;

import android.content.Context;
import android.widget.Toast;

public class AppUtil {

    public static final String TAG = "DB_Crud";

    public static void mostrarMensagemToast(Context context, String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }
}
