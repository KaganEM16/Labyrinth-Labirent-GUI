package labirentOyunu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Oyun extends JFrame implements KeyListener, ActionListener
{
	JPanel[] paneller;
	JButton[] konumlar;
	JButton basla;
	JLabel[] etiketler;
	ImageIcon[] projeResimleri;
	JLabel kapakResmi;
	int kisiKonumu;
	boolean oyunBittiMi;
		
	public Oyun()
	{
		super("Labirent");
		int en = 600, boy = 600;
		this.setSize(en+14, boy+36);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		paneller = new JPanel[3];
		for(int i=0 ; i<paneller.length ; i++) {
			paneller[i] = new JPanel();
			paneller[i].setLayout((i==1)? new GridLayout(15,15) : new FlowLayout());
			if(i==0 || i==2)
				paneller[0].add(Box.createRigidArea(new Dimension(0, 150)));
			paneller[i].setSize(en, boy);
			paneller[i].setBackground(Color.BLACK);
		}		
		this.add(paneller[0]);
		paneller[1].addKeyListener(this);
		paneller[1].setFocusable(true);
		
		etiketler = new JLabel[2];
		String[] basliklar = {"<html>Labirent Oyununa Hoşgeldiniz <br>Başlamak için Butona Tıklayınız.<br></html>"
				, "<html>Oyun Bitti <br>Oynadığınız için Teşekkürler<br></html>"};
		for(int i=0 ; i<etiketler.length ; i++) {
			etiketler[i] = new JLabel(basliklar[i]);
			etiketAyarla(etiketler[i], 30, Color.RED);
			if(i==0) {
				paneller[i].add(etiketler[i]);
			}else if(i==2) {
				paneller[i].add(etiketler[1]);
			}
		}		
		
		projeResimleri = new ImageIcon[3];
		for(int i=0 ; i<projeResimleri.length ; i++) {
			ImageIcon dosyadakiResim;
			if(i==0)
				dosyadakiResim = new ImageIcon("ProjeResimleri/"+(i+1)+".jpg");
			else
				dosyadakiResim = new ImageIcon("ProjeResimleri/"+(i+1)+".png");
			Image duzenlenecekResim = dosyadakiResim.getImage();
			Image duzenlenenResim = duzenlenecekResim.getScaledInstance((i==0)?400:40, (i==0)?200:40, Image.SCALE_SMOOTH);				
			projeResimleri[i] = new ImageIcon(duzenlenenResim);
		}
		kapakResmi = new JLabel(projeResimleri[0]);
		paneller[0].add(kapakResmi);
		
		basla = new JButton("BAŞLA");
		basla.setPreferredSize(new Dimension(300, 100));
		basla.setHorizontalAlignment(0);
		basla.setBackground(Color.RED);
		etiketAyarla(basla, 30, Color.WHITE);
		basla.setBorder(null);
		basla.addActionListener(this);
		paneller[0].add(basla);
		
		konumlar = new JButton[225];
		
		for(int i=0 ; i<konumlar.length ; i++) {
			if(i==0) {
				konumlar[i] = new JButton(projeResimleri[1]);
				konumlar[i].setContentAreaFilled(false);
				kisiKonumu = i;
			}else if(i==224) {
				konumlar[i] = new JButton(projeResimleri[2]);
				konumlar[i].setContentAreaFilled(false);
			}else {
				konumlar[i] = new JButton();
				if ((i >= 2 && i <= 4) || (i >= 7 && i <= 12) || (i >= 18 && i <= 19) || 
					    (i == 31) || (i >= 36 && i <= 37) || (i == 41) || (i >= 42 && i <= 49) || 
					    (i == 52) || (i == 53) || (i == 56) || (i >= 66 && i <= 67) || 
					    (i == 71) || (i >= 78 && i <= 81) || (i == 84) || (i >= 86 && i <= 88) || 
					    (i >= 91 && i <= 93) || (i == 99) || (i == 108) || (i >= 111 && i <= 115) || 
					    (i == 123) || (i == 126) || (i >= 132 && i <= 139) || (i == 141) || 
					    (i == 151) || (i >= 156 && i <= 158) || (i == 166) || (i == 173) || 
					    (i >= 175 && i <= 179) || (i >= 181 && i <= 184) || (i == 188) || 
					    (i >= 189 && i <= 190) || (i == 192) || (i == 194) || (i == 199) || 
					    (i >= 201 && i <= 203) || (i == 207) || (i == 214)){
					konumlar[i].setBackground(Color.WHITE);
				}else {					
					konumlar[i].setContentAreaFilled(false);
				}
			}
			konumlar[i].setBorderPainted(false);
			
			paneller[1].add(konumlar[i]);
		}
				
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{		
		paneller[0].setVisible(false);		
		this.add(paneller[1]);
		paneller[1].requestFocusInWindow();
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(!oyunBittiMi) {
			char basilanTus = e.getKeyChar();
			if(basilanTus == 'd' ||basilanTus == 'D') {			
				if(kisiKonumu % 15 < 14) {
					if(kisiKonumu + 1 == 224) {						
						oyunBittiMi = true;
						JOptionPane.showMessageDialog(null, "Labirenti tamamladınız, tebrikler :)");
					}else if(konumlar[kisiKonumu+1].getBackground() != Color.WHITE){					
						konumlar[kisiKonumu].setIcon(null);
						kisiKonumu++;
						konumlar[kisiKonumu].setIcon(projeResimleri[1]);
					}
				}
			}else if(basilanTus == 's' ||basilanTus == 'S')	{
				if(kisiKonumu <= 209) {
					if(kisiKonumu + 15 == 224) {
						oyunBittiMi = true;
						JOptionPane.showMessageDialog(null, "Labirenti tamamladınız, tebrikler :)");
					}else if(konumlar[kisiKonumu+15].getBackground() != Color.WHITE){					
						konumlar[kisiKonumu].setIcon(null);
						kisiKonumu+=15;
						konumlar[kisiKonumu].setIcon(projeResimleri[1]);
					}
				}
			}else if(basilanTus == 'a' ||basilanTus == 'A')	{
				if(kisiKonumu % 15 > 0) {
					if(kisiKonumu - 1 == 224) {
						oyunBittiMi = true;
						JOptionPane.showMessageDialog(null, "Labirenti tamamladınız, tebrikler :)");
					}else if(konumlar[kisiKonumu-1].getBackground() != Color.WHITE){					
						konumlar[kisiKonumu].setIcon(null);
						kisiKonumu--;
						konumlar[kisiKonumu].setIcon(projeResimleri[1]);
					}
				}
			}else if(basilanTus == 'w' ||basilanTus == 'w')	{
				if(kisiKonumu > 14) {
					if(kisiKonumu - 15 == 224) {
						oyunBittiMi = true;
						JOptionPane.showMessageDialog(null, "Labirenti tamamladınız, tebrikler :)");
					}else if(konumlar[kisiKonumu-15].getBackground() != Color.WHITE){
						konumlar[kisiKonumu].setIcon(null);
						kisiKonumu-=15;
						konumlar[kisiKonumu].setIcon(projeResimleri[1]);
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}
	
	public void etiketAyarla(Object nesne, int boyut, Color renk)
	{
		if(nesne instanceof JButton) {
			JButton label = (JButton)nesne;
			label.setForeground(renk);
			label.setFont(new Font("Verdana", Font.BOLD, boyut));
			label.setHorizontalAlignment(0);}
		else {
			JLabel label = (JLabel)nesne;		
			label.setForeground(renk);
			label.setFont(new Font("Verdana", Font.BOLD, boyut));
			label.setHorizontalAlignment(0);}
	}
}
