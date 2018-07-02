/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

import java.awt.Image;
import java.io.File;
import java.io.IOException; 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author KELECHI
 */
public class ImageFile {

    private String fileName = "";
    private final String root = System.getProperty("user.home");

    public String getFileName() {
        return fileName;
    }

    public ImageIcon getIcon(String imageName, int imageWidth, int imageHeight) {
        ImageIcon icon = null;
        try {
            Image image = ImageIO.read(getClass().getResource("/Images/" + imageName));
            icon = new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            System.err.println(e);
        }
        return icon;
    }

    public Image getImage(String imageName) {
        Image image = getIcon(imageName, 300, 300).getImage();
        return image;
    }

    public ImageIcon getPassport(String fileName) {
        ImageIcon passport = null;
        try {
            File file = new File(root+"\\Documents\\NIIT\\Passports\\"+fileName);
            Image image = ImageIO.read(file);
            passport = new ImageIcon(image.getScaledInstance(200, 250, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            System.err.println(e);
        }
        return passport;
    }

    public void uploadPassport(String studentName, File passport) {
        try {
            Path source = Paths.get(passport.getAbsolutePath());
            Path target = Paths.get(root+"\\Documents\\NIIT\\Passports\\" + studentName + ".png");
            Path directory = Paths.get(root+"\\Documents\\NIIT\\Passports");
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            if (Files.exists(target)) {
                String newname = studentName.concat("0");
                uploadPassport(newname, passport);
            }
            Files.copy(source, target);
            fileName = studentName + ".png";
        } catch (IOException e) {
        }
    }
}
