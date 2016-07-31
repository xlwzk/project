package zx.ffts.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImageVerifyCode {
	private int width = 70;
	private int height = 30;
	private Random rand = new Random();
	// 可选字体
	private String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体" };
	// 可选字符
	private String codes = "23456789abcdefghjkmnopqrstuwxyzABCDEFGHJKLMNPQRSTUVY";
	// 背景色
	private Color bgColor = new Color(255, 255, 255);
	// 验证码字符
	private String text;

	public String getText() {
		return text;
	}

	private Color randomColor() {
		int red = rand.nextInt(150);
		int green = rand.nextInt(150);
		int blue = rand.nextInt(150);
		return new Color(red, green, blue);
	}

	private Font randomFont() {
		int index = rand.nextInt(fontNames.length);
		String fontName = fontNames[index];
		int style = rand.nextInt(4);
		int size = rand.nextInt(5) + 24;
		return new Font(fontName, style, size);
	}

	private Character randomChar() {
		int index = rand.nextInt(codes.length());
		return codes.charAt(index);
	}

	private BufferedImage createImage() {
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setColor(bgColor);
		g.fillRect(0, 0, width, height);
		return image;
	}

	private void drawLine(BufferedImage image) {
		int num = 3;
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		for (int i = 0; i < num; i++) {
			int x1 = rand.nextInt(width);
			int y1 = rand.nextInt(height);
			int x2 = rand.nextInt(width);
			int y2 = rand.nextInt(height);
			g2.setStroke(new BasicStroke(1.5F));
			g2.setColor(Color.BLUE);
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	public BufferedImage getImageVerify() {
		BufferedImage image = createImage();
		Graphics g = image.getGraphics();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 4; i++) {
			String s = randomChar() + "";
			sb.append(s);
			float x = i * 1.0F * width / 4;
			g.setFont(randomFont());
			g.setColor(randomColor());
			g.drawString(s, (int) x, height - 5);
		}
		this.text = sb.toString();
		drawLine(image);
		return image;
	}

	public static void outputToStream(BufferedImage image, OutputStream out)
			throws IOException {
		ImageIO.write(image, "JPEG", out);
	}
}
