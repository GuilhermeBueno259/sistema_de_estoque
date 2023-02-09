package com.controle_de_estoque.visualizacao.componentes_personalizados;

import java.awt.Dimension;

import javax.swing.JLabel;

public class JLabelPersonalizado extends JLabel {
	private static final long serialVersionUID = -337864642120086479L;

	public JLabelPersonalizado(String texto, int largura, int altura) {
		super(texto);
		setPreferredSize(new Dimension(largura, altura));
	}
}
