package HuffmanCoding;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;

public class HuffmanCodingGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
    JPanel panel = new JPanel();
    JPanel panel_1 = new JPanel();
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    JButton btnTree = new JButton("Huffman Tree");
    JButton btnEncrypt = new JButton("ecrypt!");
    JScrollPane sp;
    HuffmanCoding hc= new HuffmanCoding();
    String[][] data;
    String[] column;
    private int clicks = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HuffmanCodingGUI frame = new HuffmanCodingGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	
	public HuffmanCodingGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(26, 31, 319, 37);
        
		panel.add(textField);
		textField.setColumns(10);
		
        panel_1.setBounds(21, 91, 750, 203);
		panel.add(panel_1);
		panel_1.setLayout(null);
        label.setBounds(21,320,1000,20);
        label.setFont(new Font("Tahona",Font.BOLD,20));
        label2.setBounds(21,300,620,20);
        label2.setFont(new Font("Tahona",Font.BOLD,14));
        panel.add(label);
        panel.add(label2);
        
        btnTree.setBounds(545,31,120,37);
        btnTree.setVisible(false);
        panel.add(btnTree);
		
		btnEncrypt.setBounds(423, 31, 120, 37);
		panel.add(btnEncrypt);
        btnEncrypt.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                clicks++;
                auxiliar();
            } 
        });
	}
    public void auxiliar(){
        if(clicks >1){
            panel_1.remove(sp);
        }
        hc.setText(textField.getText());
        hc.launch();
        int[][] aux = hc.getMatrix().clone();
		data=new String[6][aux[0].length+1];
        char[] aux2 = hc.getSymbols().clone();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < aux[0].length+1 ;j++){
                if(i == 0 && j != 0){
                    
                    if(j > aux2.length){
                        data[i][j] = "-";
                    }else{
                        data[i][j] = String.valueOf(aux2[j-1]);
                    }
                }else if(j != 0){
                    data[i][j] = String.valueOf(aux[i-1][j-1]);
                }
            }
        }
        data[0][0] = "Symbol";
        data[1][0] = "Frecuency";
        data[2][0] = "Parent";
        data[3][0] = "Type";
        data[4][0] = "Left";
        data[5][0] = "Right";

        column = new String[data[0].length];
        column[0] = "-" ;
        for(int i = 1; i < column.length; i++){
            column[i] = String.valueOf(i-1);
        }
        
		
		table = new JTable(data,column);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));        
        table.setColumnSelectionAllowed(true);
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumn("-").setPreferredWidth(75);
        table.getColumnModel().getColumn(column.length-1).setCellRenderer(centerRenderer);
		for(int i = 0; i < column.length-1;i++){
            table.getColumn(String.valueOf(i)).setPreferredWidth(10);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        table.setDefaultRenderer(String.class, centerRenderer);
        
        sp=new JScrollPane(table);
        sp.setBounds(0, 0, 750, 203);
		panel_1.add(sp);

        label2.setText("Encrypted text: ");
        label.setText(hc.getEncryptedText());
        btnTree.setVisible(true);
    }
}
