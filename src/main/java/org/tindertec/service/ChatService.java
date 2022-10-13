package org.tindertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tindertec.model.*;
import org.tindertec.repository.*;

@Service
public class ChatService {
	
	@Autowired	
	private IMatchRepository repoMatch;
	@Autowired	
	private IChatRepository repoChat;
	
	public List<Match> CargarMatchsEnChat(int userId) {
		List<Match> match;
		match = repoMatch.USP_LISTAR_MATCH_POR_USUARIO(userId);
		if (match == null) {
			return null;
		}
		else {
			
			return match;
		}
	}
	public List<Chat> ListarChat(int CodUsuInSession,int CodUsuarioSeleccionado) {
		List<Chat> chat;
		chat = repoChat.USP_LISTAR_CHAT_POR_USUARIO(CodUsuInSession, CodUsuarioSeleccionado);
		if (chat == null) {
			return null;
		}
		else {
			
			return chat;
		}
	}
	public String sendMensaje(int CodUsuInSession,int cod_usu_enviarmsj,String msj_enviar) {
		String msg="";
		try {
			repoChat.USP_REGISTRAR_CHAT(CodUsuInSession, cod_usu_enviarmsj, msj_enviar);
			msg="ok";
		} catch (Exception e) {
			msg=e.toString();
		}
		return msg;
		
	}
	public String CancelarMatch(int CodUsuInSession, int cod_usu_menu) {
		String msg="";
		try {
			repoMatch.USP_ELIMINAR_MATCH_POR_USUARIO(CodUsuInSession, cod_usu_menu);
			msg="ok";
		} catch (Exception e) {
			msg=e.toString();
		}
		return msg;
		
	}
}
