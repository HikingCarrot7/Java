package areatexto;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Lamina extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JTextArea areaTexto;
	
	private JScrollPane textArea;
	
	private JPanel botones, texto;
	
	private JLabel info;
	
	private JButton insertar, saltar, comprobar;
	
	public Lamina() 
	{
		setLayout(new BorderLayout());
		
		info = new JLabel("", JLabel.CENTER);
		
		texto = new JPanel();
		
		texto.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		areaTexto = new JTextArea(8, 20);
		
		textArea = new JScrollPane(areaTexto);
		
		comprobar = new JButton("Comprobar");
		
		areaTexto.setLineWrap(true);
		
		texto.add(textArea);
		texto.add(comprobar);
		
		botones = new JPanel();
		
		insertar = new JButton("Insertar");
		
		saltar = new JButton("Quitar el salto de línea");
		
		areaTexto.getDocument().addDocumentListener(new DocumentListener() 
		{

			@Override
			public void changedUpdate(DocumentEvent e) 
			{
				
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) 
			{
				areaTexto.setBackground(areaTexto.getText().trim().contentEquals("Minecraft") ? Color.green : Color.white);
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) 
			{
				areaTexto.setBackground(areaTexto.getText().trim().contentEquals("Minecraft") ? Color.green : Color.white);
				
			}
			
		});
		
		insertar.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				areaTexto.append("Terraria es lo máximo!");
				
			}
			
		});
		
		saltar.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				areaTexto.setLineWrap(!areaTexto.getLineWrap()); 
				
				saltar.setText(areaTexto.getLineWrap() ? "Quitar el salto de línea" : "Con salto de línea");
				
			}
			
		});
		
		comprobar.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				info.setText(areaTexto.getText().trim().contentEquals("Terraria es lo máximo!") ? "Estás en lo correcto bro! xD" : "");
			}
			
		});
		
		botones.add(insertar);
		botones.add(saltar);
		
		add(texto, BorderLayout.NORTH);
		add(info, BorderLayout.CENTER);
		add(botones, BorderLayout.SOUTH);
		
	}
}