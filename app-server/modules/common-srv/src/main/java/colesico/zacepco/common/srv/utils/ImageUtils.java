/*
 * Copyright © 2018-2020 Colesico
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package colesico.zacepco.common.srv.utils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Image processing utils
 *
 * @author Colesico
 */
public class ImageUtils {

    /**
     * Fills the image background with the specified color.
     *
     * @param img   the target image to fill
     * @param color the background color
     */
    private static void fillColor(BufferedImage img, Color color) {
        Graphics2D g = img.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        g.dispose();
    }

    /**
     * Crops and resizes a specific region of an image to fit target dimensions.
     *
     * @param image     the source image
     * @param dstWidth  the target width
     * @param dstHeight the target height
     * @param offsetX   the horizontal focal point offset ratio (0.0 to 1.0)
     * @param offsetY   the vertical focal point offset ratio (0.0 to 1.0)
     * @return the resulting icon image
     */
    public static Image iconize(Image image, int dstWidth, int dstHeight, float offsetX, float offsetY) {
        BufferedImage dstImage = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
        //fillColor(dstImage, Color.WHITE);


        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        float ratioWidth = (float) imgWidth / (float) dstWidth;
        float ratioHeight = (float) imgHeight / (float) dstHeight;

        float ratio;
        if (ratioWidth < ratioHeight) {
            ratio = ratioWidth;
        } else {
            ratio = ratioHeight;
        }

        Graphics2D g = dstImage.createGraphics();

        int pickWidth = Math.round(ratio * dstWidth);
        int pickHeight = Math.round(ratio * dstHeight);


        int x = Math.round((offsetX * imgWidth - 0.5f * pickWidth));
        int y = Math.round((offsetY * imgHeight - 0.5f * pickHeight));

        if (x < 0) {
            x = 0;
        }

        if (y < 0) {
            y = 0;
        }

        if (x + pickWidth > imgWidth) {
            x = imgWidth - pickWidth;
        }

        if (y + pickHeight > imgHeight) {
            y = imgHeight - pickHeight;
        }

        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(image, 0, 0, dstWidth, dstHeight, x, y, x + pickWidth, y + pickHeight, Color.WHITE, null);
        g.dispose();

        return dstImage;
    }

    /**
     * Scales down an image proportionally to fit within maximum dimensions.
     * Does not scale up if the image is already smaller than the target size.
     *
     * @param image        the source image
     * @param dstMaxWidth  the maximum target width
     * @param dstMaxHeight the maximum target height
     * @return the resized image
     */
    public static Image minimize(Image image, int dstMaxWidth, int dstMaxHeight) {

        int srcWidth = image.getWidth(null);
        int srcHeight = image.getHeight(null);


        float ratioWidth = (float) dstMaxWidth / (float) srcWidth;
        float ratioHeight = (float) dstMaxHeight / (float) srcHeight;

        float ratio;
        if (ratioWidth < ratioHeight) {
            ratio = ratioWidth;
        } else {
            ratio = ratioHeight;
        }

        if (ratio > 1.0f) {
            ratio = 1.0f;
        }

        int dstWidth = Math.round((float) srcWidth * ratio);
        int dstHeight = Math.round((float) srcHeight * ratio);

        BufferedImage resizedImage = new BufferedImage(dstWidth, dstHeight, BufferedImage.TYPE_INT_RGB);
        // fillColor(resizedImage, Color.WHITE);

        Graphics2D g = resizedImage.createGraphics();

        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(image, 0, 0, dstWidth, dstHeight, Color.WHITE, null);
        g.dispose();
        return resizedImage;
    }

    /**
     * Converts a byte array into an Image object.
     *
     * @param imgBytes the raw image bytes
     * @return the decoded Image
     * @throws RuntimeException if an I/O error occurs during reading
     */
    public static Image toImage(byte[] imgBytes) {
        BufferedImage img = null;
        ByteArrayInputStream input = null;
        try {
            input = new ByteArrayInputStream(imgBytes);
            img = ImageIO.read(input);
            return img;
        } catch (IOException e) {
            throw new RuntimeException("Error reading image bytes");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Converts an image to a JPEG byte array with the specified quality.
     *
     * @param image   the image to convert
     * @param quality the compression quality from 0.0 to 1.0
     * @return the JPEG compressed byte array
     */
    public static byte[] toJpegBytes(Image image, float quality) {
        return toFormatBytes(image, "jpeg", quality);
    }

    /**
     * Converts an image to a PNG byte array with the specified quality.
     *
     * @param image   the image to convert
     * @param quality the compression quality from 0.0 to 1.0
     * @return the PNG encoded byte array
     */
    public static byte[] toPngBytes(Image image, float quality) {
        return toFormatBytes(image, "png", quality);
    }

    /**
     * Converts an image into a byte array of the specified format and quality.
     *
     * @param image   the image to convert
     * @param format  the target format name (e.g., "jpeg", "png")
     * @param quality the compression quality value between 0.0 and 1.0
     * @return the encoded image byte array
     * @throws RuntimeException if an error occurs during encoding
     */
    public static byte[] toFormatBytes(Image image, String format, float quality) {
        ImageOutputStream imageOutput = null;
        ImageWriter writer = null;
        try {
            Iterator iter = ImageIO.getImageWritersByFormatName(format);
            writer = (ImageWriter) iter.next();
            ImageWriteParam iwp = writer.getDefaultWriteParam();
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(quality);   // a value between 0 and 1
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            imageOutput = new MemoryCacheImageOutputStream(bos);
            writer.setOutput(imageOutput);
            IIOImage iiImage = new IIOImage((RenderedImage) image, null, null);
            writer.write(null, iiImage, iwp);
            return bos.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (writer != null) {
                    writer.dispose();
                }
            } catch (Exception ex) {

            }
            try {
                if (imageOutput != null) {
                    imageOutput.close();
                }
            } catch (Exception ex) {

            }
        }

    }
}
