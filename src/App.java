import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class App {

    public static final String CACHE_NAME = ".runique2_cache";
    public static final String CACHE_DATA_NAME = ".runique2_data";
    private File cacheFile = new File(System.getProperty("user.home") + "/" + CACHE_NAME + "/");
    private File dataFile = new File(System.getProperty("user.home") + "/" + CACHE_DATA_NAME + "/");
    boolean cacheExists = cacheFile.exists();
    boolean dataExists = dataFile.exists();

    public JButton button_delete_cache;
    private JButton button_delete_data;
    public JPanel panelMain;
    public JTextArea textArea1;
    private final static String newline = "\n";

    boolean deleteDirectory(File directoryToBeDeleted, String directory) {
        File[] allContents = directoryToBeDeleted.listFiles();
        textArea1.append("Deleting the " + (directory.equalsIgnoreCase("cache") ? "cache" : "data") + newline);
        if (allContents != null) {
            textArea1.append("Deleting the " + (directory.equalsIgnoreCase("cache") ? "cache" : "data") + newline);
            for (File file : allContents) {
                textArea1.append("Deleting file: " + file.getName() + newline);
                deleteDirectory(file, directory);
            }
            textArea1.append("Delete the " + (directory.equalsIgnoreCase("cache") ? "" : "") + "Restart your client." + newline);

        }
        return directoryToBeDeleted.delete();
    }


    public App() {
        button_delete_cache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cacheExists) {
                    Object[] options = { "DELETE", "CANCEL" };
                    int n = JOptionPane.showOptionDialog(null, "Click DELETE if your client is completely closed", "Your Runique Client Must be Closed",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    if (n == JOptionPane.YES_OPTION)
                        deleteDirectory(cacheFile, "cache");
                } else {
                    textArea1.append("Cache Doesn't Exist " + newline);
                }

            }
        });
        button_delete_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dataExists) {
                    Object[] options = { "DELETE", "CANCEL" };
                    int n = JOptionPane.showOptionDialog(null, "Click DELETE if your client is completely closed", "Your Runique Client Must be Closed",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                            null, options, options[0]);
                    if (n == JOptionPane.YES_OPTION)
                        deleteDirectory(dataFile, "data");
                } else {
                    textArea1.append("Data Doesn't Exist " + newline);
                }

            }
        });
    }
}
