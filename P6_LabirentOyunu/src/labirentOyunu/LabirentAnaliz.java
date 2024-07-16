package labirentOyunu;

//    OLUŞTURULMAK İSTENEN LABİRENTTE YER ALACAK DUVARLARIN KONUMLARININ ÖĞRENİLMESİ İÇİN TASARLANMIŞTIR.

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class LabirentAnaliz extends JFrame implements ActionListener
{
	JButton[] konumlar;
	ArrayList<Integer> sayilar = new ArrayList<>();
	
	public LabirentAnaliz() {
		
		super("Labirent");
		int en = 600, boy = 600;
		this.setSize(en+14, boy+36);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(15,15));
		this.setResizable(false);
		
		konumlar = new JButton[225];
		for(int i=0 ; i<konumlar.length ; i++) {
			konumlar[i] = new JButton();
			konumlar[i].addActionListener(this);
			this.add(konumlar[i]);
		}
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		JButton basilan = (JButton)e.getSource();
		for(int i=0 ; i<konumlar.length ; i++) {
			if(basilan == konumlar[i]) {
				if(i==224) {
					sayilar.sort(null);
					System.out.println(sayilar);
				}else {
					konumlar[i].setBackground(Color.BLACK);
					sayilar.add(i);
					System.out.println(i);
				}
			}
		}
	}
}
