package GameHandlers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class Animation {

    // Image of animation.
    private BufferedImage[] animImage;

    // Width of one frame in animated image.
    private int frameWidth;

    // Height of the frame(image).
    private int frameHeight;

    // Number of frames in the animation image.
    private int numberOfFrames;

    // Amount of time between frames in milliseconds. (How many time in milliseconds will be one frame shown before showing next frame?)
    private long frameTime;

    // Time when the frame started showing. (We use this to calculate the time for the next frame.)
    private long startingFrameTime;

    // Time when we show next frame. (When current time is equal or greater then time in "timeForNextFrame", it's time to move to the next frame of the animation.)
    private long timeForNextFrame;

    // Current frame number.
    private int currentFrameNumber;

    // Should animation repeat in loop?
    private boolean loop;

    /** x and y coordinates. Where to draw the animation on the screen? */
    public int x;
    public int y;
    
    // In milliseconds. How long to wait before starting the animation and displaying it?
    private long showDelay;
    
    // At what time was animation created.
    private long timeOfAnimationCreation;

    /**
     * Creates animation.
     * 
     * @param animImage Image of animation.
     * @param frameWidth Width of the frame in animation image "animImage".
     * @param frameHeight Height of the frame in animation image "animImage" - height of the animation image "animImage".
     * @param numberOfFrames Number of frames in the animation image.
     * @param frameTime Amount of time that each frame will be shown before moving to the next one in milliseconds.
     * @param loop Should animation repeat in loop?
     * @param x x coordinate. Where to draw the animation on the screen?
     * @param y y coordinate. Where to draw the animation on the screen?
     * @param showDelay In milliseconds. How long to wait before starting the animation and displaying it?
     */
    public Animation(BufferedImage[] animImage, int frameWidth, int frameHeight, int numberOfFrames, long frameTime, boolean loop, int x, int y, long showDelay)
    {
        this.animImage = animImage;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.numberOfFrames = numberOfFrames;
        this.frameTime = frameTime;
        this.loop = loop;

        this.x = x;
        this.y = y;
        
        this.showDelay = showDelay;
        
        timeOfAnimationCreation = System.currentTimeMillis();

        startingFrameTime = System.currentTimeMillis() + showDelay;
        timeForNextFrame = startingFrameTime + this.frameTime;
        currentFrameNumber = 0;
    }


    /**
     * Changes the coordinates of the animation.
     * 
     * @param x x coordinate. Where to draw the animation on the screen?
     * @param y y coordinate. Where to draw the animation on the screen?
     */
    public void changeCoordinates(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * It checks if it's time to show next frame of the animation.
     * It also checks if the animation is finished.
     */
    private void Update()
    {
        if(timeForNextFrame <= System.currentTimeMillis())
        {
            // Next frame.
            currentFrameNumber++;

            // If the animation is reached the end, we restart it by setting current frame to zero. If the animation isn't loop then we set that animation isn't active.
            if(currentFrameNumber >= numberOfFrames)
            {
            	if(loop)
            		currentFrameNumber = 0;       
            	else
            		currentFrameNumber = numberOfFrames-1;
            }

            // Set time for the next frame.
            startingFrameTime = System.currentTimeMillis();
            timeForNextFrame = startingFrameTime + frameTime;
        }
    }

    /**
     * Draws current frame of the animation.
     * 
     * @param g2d Graphics2D
     */
    public void Draw(Graphics2D g2d)
    {
        this.Update();
        
        // Checks if show delay is over.
        if(this.timeOfAnimationCreation + this.showDelay <= System.currentTimeMillis())
        	g2d.drawImage(animImage[currentFrameNumber], x, y, frameWidth, frameHeight, null);
    }
}