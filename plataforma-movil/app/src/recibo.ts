import { Platform } from "react-native";
import { Directory, File, Paths } from "expo-file-system";
import * as Sharing from "expo-sharing";
import { urlRecibo } from "./api";

export async function abrirRecibo(idPedido: number) {
  const url = urlRecibo(idPedido);
  if (Platform.OS === "web") {
    window.open(url, "_blank");
    return;
  }
  const destino = new File(new Directory(Paths.cache), `Recibo-${String(idPedido).padStart(6, "0")}.pdf`);
  if (destino.exists) destino.delete();
  const archivo = await File.downloadFileAsync(url, destino);
  if (await Sharing.isAvailableAsync()) {
    await Sharing.shareAsync(archivo.uri, { mimeType: "application/pdf", dialogTitle: "Recibo del pedido", UTI: "com.adobe.pdf" });
  }
}
