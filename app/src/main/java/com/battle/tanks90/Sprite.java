package com.battle.tanks90;

import gameEngine.com.Actor.Actor;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.Log;

public class Sprite {
	private String ImageName;
	private Bitmap tileSheet; // hold image
	public int currentAnimation = 0;// = "idle"; // current animation sequence
	private int currentFrame = 0; // current frame being played
	private int xpos = 0; // x position
	private int ypos = 0; // y position
	private int waitDelay = 0; // delay before the next frame
	// public static Paint paint = new Paint();
	// Class contains Information about one frame
	int loadType = 0; // 0 = load image name//1 load num of
	// 2. load module
	Module[] modules;
	Frame[] frames;
	Anim[] anims;
	boolean canLoop = false;
	boolean ShowLastFrame = false;
	int modulesLen = 0;
	int frameLen = 0;
	int animLen = 0;
	public static Paint paint = new Paint();
	// Frame
	public static Matrix matrix = new Matrix();

	public Sprite(String path, boolean isUTF8) {
		loadSpriteFromPlainText(path, isUTF8);
	}

	public Sprite(String path) {
		try {
			loadSprite(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class Module {
//		public String name = "NO_NAME";
		int id;
		int type;
		int color;
		public int x, y, width, height;
		// public Rect rect = new Rect();
	
		public Bitmap image = null;

		public Module(int _id, int _type,int _color, int _x, int _y, int _w, int _h) {
			id = _id;
			type = _type;
			color =_color;
			x = _x;
			y = _y;
			width = _w;
			height = _h;
			//image = Bitmap.createImage(width, height);
			if(type == 0)
			image = Bitmap.createBitmap(tileSheet, x, y, width, height, null, false);//here
		}

		public void draw(Canvas g, int x, int y, int flag) {
			// now flag is dont use
			if(type == 0)			
			g.drawBitmap(image, x, y, null);//here
			else if(type == 1)
			{
				paint.setStyle(Style.STROKE);
				paint.setColor(color);
				g.drawRect(x, y,x+ width, y+height, paint);
			}	
			else if(type == 2)
			{
				paint.setStyle(Style.FILL);
				paint.setColor(color);
				g.drawRect(x, y,x+ width, y+height, paint);
			}	
		}
	}

	public class Frame {
		// frame is array of module
		int id;
		int numofModule;
		int[] arrayModuleIndex;
		int[] arrayModuleID;
		int[] postionX;
		int[] postionY;
		int[] flags;

	}

	public class Anim {
		// frame is array of module
		int id;
		int numOfFrame;
		int[] arrayFrameIndex;
		int[] arrayFrameID;
		int[] postionX;
		int[] postionY;
		int[] timeDelay;
	}

	// hold list frame of one animation

	public Sprite() {
		// TODO Auto-generated constructor stub
	}

	// load Image and xml data
	public String loadNameOfImage(String strBuilder, String name) {
		int index1, index2;
		String line = "";
		if (strBuilder.contains("IMAGE")) {
			index1 = strBuilder.indexOf(" ");
			index2 = strBuilder.indexOf(" ", index1 + 1);
			line = strBuilder.substring(index1 + 1, index2);
			index1 = strBuilder.indexOf(" ", index2);
			line = strBuilder.substring(index1 + 1);
			return line.trim();
		}
		return "";
	}

	public int loadSizeOFArray(String strBuilder) {
		int i = -1;
		// int type = -1;
		if (strBuilder.contains("NUM_OF_MODULE")) {

			i = strBuilder.indexOf(" ");
			modulesLen = Integer.parseInt(strBuilder.substring(i + 1));
			modules = new Module[modulesLen];
			// Log.d("number of Module : ",String.valueOf(modulesLen));
		} else if (strBuilder.contains("NUM_OF_FRAME")) {
			i = strBuilder.indexOf(" ");
			frameLen = Integer.parseInt(strBuilder.substring(i + 1));
			frames = new Frame[frameLen];
			// Log.d("number of Frame : ",String.valueOf(modulesLen));
		} else if (strBuilder.contains("NUM_OF_ANIM")) {
			i = strBuilder.indexOf(" ");
			animLen = Integer.parseInt(strBuilder.substring(i + 1));
			anims = new Anim[animLen];
			// Log.d("number of Anim : ",String.valueOf(modulesLen));
			loadType++;
		}
		return i;
	}

	public Module loadOneModule(String strBuilder) {
		// MODULE num x y w h name
		Module mod = null;
		int index1, index2;
		String line = "";
		int x, y, w, h;

		if (strBuilder.contains("MODULE")) {
			index1 = strBuilder.indexOf(" ");
			// num
			index2 = strBuilder.indexOf(" ", index1 + 1);
			line = strBuilder.substring(index1 + 1, index2);
			// x
			index1 = strBuilder.indexOf(" ", index2 + 1);
			line = strBuilder.substring(index2 + 1, index1);
			x = Integer.parseInt(line);
			// y
			index2 = strBuilder.indexOf(" ", index1 + 1);
			line = strBuilder.substring(index1 + 1, index2);
			y = Integer.parseInt(line);
			// w
			index1 = strBuilder.indexOf(" ", index2 + 1);
			line = strBuilder.substring(index2 + 1, index1);
			w = Integer.parseInt(line);
			// h
			index2 = strBuilder.indexOf(" ", index1 + 1);
			if (index2 > 0) {
				line = strBuilder.substring(index1 + 1, index2);
				h = Integer.parseInt(line.trim());
				line = strBuilder.substring(index2 + 1);
				line.trim();
			} else {
				line = strBuilder.substring(index1 + 1);
				h = Integer.parseInt(line.trim());
				line = "UNKNOW";
			}
			// name

			line = strBuilder.substring(index2 + 1);
			line.trim();
			mod = new Module(0, 0,0, x, y, w, h);
			return mod;
		}
		return null;
	}

	public int loadInFoOneFrame(String strBuilder, int idFrame, int idModule) {
		// boolean id = false;
		int count = 0;
		int index1, index2 = 0;
		String line = "";
		if (strBuilder.contains("FRAME ")) {
			// isFrame = true;

			index1 = strBuilder.indexOf("COUNT=");
			index2 = strBuilder.indexOf("=", index1);
			index1 = strBuilder.indexOf(" ", index2);

			line = strBuilder.substring(index2 + 1, index1);
			count = Integer.parseInt(line.trim());
			frames[idFrame] = new Frame();
			frames[idFrame].numofModule = count;
			frames[idFrame].arrayModuleIndex = new int[count];
			frames[idFrame].postionX = new int[count];
			frames[idFrame].postionY = new int[count];
			frames[idFrame].flags = new int[count];
		} else if (strBuilder.contains("FRAME_MODULE")) {
			index1 = strBuilder.indexOf(" ");
			// num
			index2 = strBuilder.indexOf(" ", index1 + 1);
			line = strBuilder.substring(index1 + 1, index2);
			frames[idFrame].arrayModuleIndex[idModule] = Integer.parseInt(line);
			// x
			index1 = strBuilder.indexOf(" ", index2 + 1);
			line = strBuilder.substring(index2 + 1, index1);
			frames[idFrame].postionX[idModule] = Integer.parseInt(line);
			// y
			index2 = strBuilder.indexOf(" ", index1 + 1);
			if (index2 < 0)
				line = strBuilder.substring(index1 + 1);
			else
				line = strBuilder.substring(index1 + 1, index2);
			frames[idFrame].postionY[idModule] = Integer.parseInt(line);
			idModule++;
		}
		return idModule;
	}

	public int loadInFoOneAnim(String strBuilder, int idAnim, int idFrame) {
		// boolean id = false;
		int count = 0;
		int index1, index2 = 0;
		String line = "";
		if (strBuilder.contains("ANIM ")) {
			// isFrame = true;
			index1 = strBuilder.indexOf("COUNT=");
			index2 = strBuilder.indexOf("=", index1);
			index1 = strBuilder.indexOf(" ", index2);

			line = strBuilder.substring(index2 + 1, index1);
			count = Integer.parseInt(line.trim());
			anims[idAnim] = new Anim();
			anims[idAnim].numOfFrame = count;
			anims[idAnim].arrayFrameIndex = new int[count];
			anims[idAnim].postionX = new int[count];
			anims[idAnim].postionY = new int[count];
			anims[idAnim].timeDelay = new int[count];
		} else if (strBuilder.contains("ANIM_FRAME")) {
			index1 = strBuilder.indexOf(" ");
			// num
			index2 = strBuilder.indexOf(" ", index1 + 1);
			line = strBuilder.substring(index1 + 1, index2);
			anims[idAnim].arrayFrameIndex[idFrame] = Integer.parseInt(line);
			// x
			index1 = strBuilder.indexOf(" ", index2 + 1);
			line = strBuilder.substring(index2 + 1, index1);
			anims[idAnim].postionX[idFrame] = Integer.parseInt(line);
			// y
			index2 = strBuilder.indexOf(" ", index1 + 1);
			line = strBuilder.substring(index1 + 1, index2);
			anims[idAnim].postionY[idFrame] = Integer.parseInt(line);
			// time
			index1 = strBuilder.indexOf(" ", index2 + 1);
			if (index1 < 0)
				line = strBuilder.substring(index2 + 1);
			else
				line = strBuilder.substring(index2 + 1, index1);
			anims[idAnim].timeDelay[idFrame] = Integer.parseInt(line);
			idFrame++;
		}
		return idFrame;
	}

	public synchronized void loadSprite(String path) throws IOException, XmlPullParserException {
		// listBitmap = new ArrayList<Bitmap>();
		// listBitmap.clear();
		String directorystr = "";
		if(path.lastIndexOf("/") > 0)
		 directorystr = path.substring(0, path.lastIndexOf("/"));
		Log.e("Directory : ", directorystr);
		XmlPullParser xrp = null;
		InputStream istr = GameLib.mainView.getContext().getAssets().open(path);
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(false);
		xrp = factory.newPullParser();
		xrp.setInput(istr, "UTF-8");
	
		int eventType = 0;
		eventType = xrp.getEventType();
		String tagName = "";
	
		int coutModule = 0;
		int coutFrame = 0;
		int coutAnim = 0;
		while (eventType != XmlPullParser.END_DOCUMENT) {
			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;
			case XmlPullParser.START_TAG:
				tagName = xrp.getName();
				if (tagName.equals("Image")) {
					if(directorystr.length() > 0)
						ImageName = directorystr + "/" + xrp.getAttributeValue(null, "Path");
					else 
						ImageName =  xrp.getAttributeValue(null, "Path");
					//	Log.d("Path of Image", " " + ImageName);
					tileSheet = loadImageFromAsset(ImageName);
				} else if (tagName.equals("Module")) {
					int id, type, color = 0, x, y, w, h;
					id = Integer.parseInt(xrp.getAttributeValue(null, "ID"));
					type = Integer.parseInt(xrp.getAttributeValue(null, "Type"));
					String color_str = xrp.getAttributeValue(null, "Color");
					if(color_str.length() > 0){
						color = (int) Long.parseLong( color_str,16);
					}
					x = Integer.parseInt(xrp.getAttributeValue(null, "X"));
					y = Integer.parseInt(xrp.getAttributeValue(null, "Y"));
					w = Integer.parseInt(xrp.getAttributeValue(null, "W"));
					h = Integer.parseInt(xrp.getAttributeValue(null, "H"));
					modules[coutModule] = new Module(id, type,color, x, y, w, h);
					coutModule++;
				} else if (tagName.equals("Frame")) {
					// <Frame Index="0" ID="2000" NumOfModules="2" Desc="Frame 1">				   					
					frames[coutFrame] = new Frame();
					frames[coutFrame].id = Integer.parseInt(xrp.getAttributeValue(null, "ID"));
					frames[coutFrame].numofModule = Integer.parseInt(xrp.getAttributeValue(null, "NumOfModules"));
					frames[coutFrame].arrayModuleIndex = new int[frames[coutFrame].numofModule];
					frames[coutFrame].arrayModuleID = new int[frames[coutFrame].numofModule];
					frames[coutFrame].postionX = new int[frames[coutFrame].numofModule];
					frames[coutFrame].postionY = new int[frames[coutFrame].numofModule];
					frames[coutFrame].flags = new int[frames[coutFrame].numofModule];
				} else if (tagName.equals("FModule")) {
					//	<FModule Index="0" Module_ID="1000" OX="0" OY="0" Flags="0" />
					int index = Integer.parseInt(xrp.getAttributeValue(null, "Index"));
					//frames[coutFrame].arrayModuleIndex[index] = Integer.parseInt(xrp.getAttributeValue(null, "Index"));
					frames[coutFrame].arrayModuleID[index] = Integer.parseInt(xrp.getAttributeValue(null, "Module_ID"));
					frames[coutFrame].postionX[index] = Integer.parseInt(xrp.getAttributeValue(null, "OX"));
					frames[coutFrame].postionY[index] = Integer.parseInt(xrp.getAttributeValue(null, "OY"));
					frames[coutFrame].flags[index] = Integer.parseInt(xrp.getAttributeValue(null, "Flags"));
				} else if (tagName.equals("Animation")) {
					//<Animation Index="0" ID="3000" NumOfFrames="2" Desc="Animation 1">
					anims[coutAnim] = new Anim();
					anims[coutAnim].id = Integer.parseInt(xrp.getAttributeValue(null, "ID"));
					anims[coutAnim].numOfFrame = Integer.parseInt(xrp.getAttributeValue(null, "NumOfFrames"));
					anims[coutAnim].arrayFrameIndex = new int[anims[coutAnim].numOfFrame];
					anims[coutAnim].arrayFrameID = new int[anims[coutAnim].numOfFrame];
					anims[coutAnim].postionX = new int[anims[coutAnim].numOfFrame];
					anims[coutAnim].postionY = new int[anims[coutAnim].numOfFrame];
					anims[coutAnim].timeDelay = new int[anims[coutAnim].numOfFrame];
					
				} else if (tagName.equals("AFrame")) {
					//	 <AFrame Index="0" Module_ID="2000" OX="0" OY="0" Time="2" />
					int index = Integer.parseInt(xrp.getAttributeValue(null, "Index"));
					//frames[coutFrame].arrayModuleIndex[index] = Integer.parseInt(xrp.getAttributeValue(null, "Index"));
					anims[coutAnim].arrayFrameID[index] = Integer.parseInt(xrp.getAttributeValue(null, "Frame_ID"));
					anims[coutAnim].postionX[index] = Integer.parseInt(xrp.getAttributeValue(null, "OX"));
					anims[coutAnim].postionY[index] = Integer.parseInt(xrp.getAttributeValue(null, "OY"));
					anims[coutAnim].timeDelay[index] = Integer.parseInt(xrp.getAttributeValue(null, "Time"));					
				   //   <AFrame Index="0" Module_ID="2000" OX="0" OY="0" Time="2" />
				}
				break;
			case XmlPullParser.TEXT:
				//  <Num_of_modules>6</Num_of_modules>
				//  <Num_of_frames>6</Num_of_frames>
				//  <Num_of_animations>1</Num_of_animations>
				//	Log.d("text", xrp.getText());
				if (tagName.equals("Num_of_modules")) {
					String str = xrp.getText();
					if (str.charAt(0) != '\n') {
						modulesLen = Integer.parseInt(str);
						modules = new Module[modulesLen];
						str = null;
					}
				} else if (tagName.equals("Num_of_frames")) {
					String str = xrp.getText();
					if (str.charAt(0) != '\n') {
						frameLen = Integer.parseInt(str);
						frames = new Frame[frameLen];
						str = null;
					}
				} else if (tagName.equals("Num_of_animations")) {
					String str = xrp.getText();
					if (str.charAt(0) != '\n') {
						animLen = Integer.parseInt(xrp.getText());
						anims = new Anim[animLen];
						str = null;
					}
				}
				break;
			case XmlPullParser.END_TAG:
				tagName = xrp.getName();
				if (tagName.equals("Frame")) {
					//
					for(int i =0;i <frames[coutFrame].numofModule;i++)
					{
						for(int j =0;j <modulesLen;j++){
							if(frames[coutFrame].arrayModuleID[i] == modules[j].id)
							{
								frames[coutFrame].arrayModuleIndex[i] = j;
							}
						}
					}
					coutFrame++;
				}else if (tagName.equals("Animation")) {
					//
					for(int i =0;i <anims[coutAnim].numOfFrame;i++)
					{
						for(int j =0;j <frameLen;j++){
							if(anims[coutAnim].arrayFrameID[i] == frames[j].id)
							{
								anims[coutAnim].arrayFrameIndex[i] = j;
							}
						}
					}
					coutAnim++;
				}
				break;
			}
			eventType = xrp.next();
		}
			tileSheet = null;
	}

	public void loadSpriteFromPlainText(String path, boolean isUTF8) {
		try {
			String strBuilder;
			String allStrInFile = GameLib.readTextFile(path, true);
			// int index2 = -1;
			boolean isParse = true;
			int numRecordOfOneType = 0;
			// int frame_anim_index =0;
			int idFrame = 0, idModule = 0;
			int idAnim = 0;
			int index = 0;
			int index1 = -1;
			while (isParse) {
				//
				index = allStrInFile.indexOf('\n');
				if (index < 0) {
					isParse = false;
					continue;
				}

				strBuilder = allStrInFile.substring(0, index).trim();
				allStrInFile = allStrInFile.substring(index,
						allStrInFile.length()).trim();
				if (strBuilder == null) {
					isParse = false;
					continue;
				}
				// strBuilder = new StringBuilder(line.trim());
				index1 = strBuilder.indexOf("//");
				if (index1 >= 0) {
					strBuilder = strBuilder.substring(index1);
				}
				strBuilder = strBuilder.trim();
				if (strBuilder.length() < 2)// chuoi khong su dung// khong xe
					// den dau ngoac
					continue;
				// load kich thuoc cua mang modules,frame va anim
				switch (loadType) {
				case 0:
					ImageName = loadNameOfImage(strBuilder, "IMAGE");
					// create bit map here
					tileSheet = GameLib.loadImageFromAsset(ImageName);
					//CGame.log("Image File" + ImageName);
					loadType++;
					break;
				case 1:
					loadSizeOFArray(strBuilder);// load
					break;
				case 2:
					// loadType++;
					Module mod = loadOneModule(strBuilder);
					if (mod != null) {
						modules[numRecordOfOneType] = mod;
						numRecordOfOneType++;
					}
					if (numRecordOfOneType >= modulesLen) {
						numRecordOfOneType = 0;
						tileSheet = null;// delete it to save memory
						loadType++;
					}
					break;
				case 3:
					idModule = loadInFoOneFrame(strBuilder, idFrame, idModule);
					if (idModule > 0) {
						if (frames[idFrame].numofModule <= idModule) {
							idFrame++;
							idModule = 0;
						}
					}
					if (idFrame >= frameLen) {
						idFrame = 0;
						loadType++;
					}
					break;
				case 4:
					idFrame = loadInFoOneAnim(strBuilder, idAnim, idFrame);
					if (idFrame > 0) {
						if (anims[idAnim].numOfFrame <= idFrame) {
							idAnim++;
							idFrame = 0;
						}

					}
					if (idAnim >= animLen)
						loadType++;
					break;
				}

				if (loadType > 4)
					isParse = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void drawAModule(Canvas g, int id, int x, int y, int flag) {
		modules[id].draw(g,x,y,flag);
		//g.drawBitmap(modules[id].image, x, y, null);
		
	}		
	
	public void drawAModule(Canvas g, int id,int angle,float Scalex,float  Scaley, int x, int y ) {		


		matrix.reset();

	    matrix.postRotate(angle,modules[id].width/2, modules[id].height/2);	//rotation at center of module image
	    matrix.postScale(Scalex,Scaley);
		matrix.postTranslate(x,y); //move it into x, y position
		g.drawBitmap(modules[id].image,matrix, null);
	
//	g.drawBitmap(modules[id].image, x, y, null);

	}	
	
	public void drawAModule(Canvas g, int id, int x, int y,int flipx,int fligy) {
		g.drawBitmap(modules[id].image, x, y, null);
		// Flip
		//    matrix.setScale(-1,1);
		//    matrix.postTranslate(x, y);		
		//   g.drawBitmap(modules[id].image, matrix, null);
	}	

	public void drawAFrame(Canvas g, int id,int angle,float Scalex,float Scaley, int x, int y ) {
		for (int i = 0; i < frames[id].numofModule; i++) {

			int moduleisPaint = frames[id].arrayModuleIndex[i];
			
			if(angle != 0){
				//centerx= w/2+x;
				//centery= h/2+y;
				int centerx = (modules[moduleisPaint].width/2 + frames[id].postionX[i]);
				int centery = (modules[moduleisPaint].height/2 + frames[id].postionY[i]);
				Point newcenterPoint = rotationPoint(centerx ,centery, angle);
				//x= centerx - w/2
				//x= centery - h/2
				int new_x=newcenterPoint.x -modules[moduleisPaint].width/2 ;
				int new_y=newcenterPoint.y -modules[moduleisPaint].height/2; 
			  drawAModule(g, moduleisPaint,angle,Scalex,Scaley,x + new_x, y+ new_y);			  
			}else			
			drawAModule(g, moduleisPaint,angle,Scalex,Scaley, (int)(x + frames[id].postionX[i]*Scalex),  (int)(y	+ frames[id].postionY[i]* Scaley));
		}
	}
	public void drawAFrame(Canvas g, int id, int x, int y) {
		// try {
		for (int i = 0; i < frames[id].numofModule; i++) {

			int moduleisPaint = frames[id].arrayModuleIndex[i];
			drawAModule(g, moduleisPaint, x + frames[id].postionX[i], y
					+ frames[id].postionY[i],frames[id].flags[i]);
		}

	}

	// Draw sprite onto screen
	public void setAnim(int id, boolean loop, boolean _showLastFrame) {
		currentAnimation = id;
		canLoop = loop;
		ShowLastFrame = _showLastFrame;
		waitDelay = 0;
		currentFrame = 0;

	}

	public void setAnim(int id, int x, int y, boolean loop,
			boolean _showLastFrame) {
		currentAnimation = id;
		canLoop = loop;
		ShowLastFrame = _showLastFrame;
		waitDelay = 0;
		currentFrame = 0;
		xpos = x;
		ypos = y;

	}
	public void drawAnim(Canvas g, int angle,int Scalex,int Scaley, int x, int y){
		if (currentFrame < anims[currentAnimation].numOfFrame)
			//drawAFrame(mainCanvas,0,20,1,1,200,200);
			drawAFrame(g, anims[currentAnimation].arrayFrameIndex[currentFrame],angle,Scalex,Scaley, x
					+ anims[currentAnimation].postionX[currentFrame], y
					+ anims[currentAnimation].postionY[currentFrame]);
		update();
	}
	public void drawAnim(Canvas g, int x, int y) {
		if (currentFrame < anims[currentAnimation].numOfFrame)
			drawAFrame(g, anims[currentAnimation].arrayFrameIndex[currentFrame], x
					+ anims[currentAnimation].postionX[currentFrame], y
					+ anims[currentAnimation].postionY[currentFrame]);
		update();
	}

	public void drawAnim(Canvas g) {
		if (currentFrame < anims[currentAnimation].numOfFrame)
			drawAFrame(g, anims[currentAnimation].arrayFrameIndex[currentFrame], xpos
					+ anims[currentAnimation].postionX[currentFrame], ypos
					+ anims[currentAnimation].postionY[currentFrame]);
			// drawAFrame(g, anims[currentAnimation].arrayFrameIndex[currentFrame],
					// xpos + anims[currentAnimation].postionX[currentFrame], ypos
							// + anims[currentAnimation].postionY[currentFrame]);
		else if (ShowLastFrame) {
			int frameIndex = anims[currentAnimation].numOfFrame - 1;
			drawAFrame(g, anims[currentAnimation].arrayFrameIndex[frameIndex],
					xpos + anims[currentAnimation].postionX[frameIndex], ypos
							+ anims[currentAnimation].postionY[frameIndex]);
		}

		update();
	}

	// updates the frame counter to the next frame
	public void update() {
	
		waitDelay++;
		if (currentFrame <= anims[currentAnimation].numOfFrame - 1) {
			if (waitDelay > anims[currentAnimation].timeDelay[currentFrame]) {
				currentFrame++;
				waitDelay = 0;
			}
		}
		if (currentFrame >= anims[currentAnimation].numOfFrame) {
			if (canLoop) {
				currentFrame = 0;
				waitDelay = 0;
			} else if (ShowLastFrame) {
				//	currentFrame = anims[currentAnimation].animFramesLen - 1;
			}
		}
	}
	public boolean hasAnimationFinished() {

		if (currentFrame >= anims[currentAnimation].numOfFrame)
			return true;
		if (currentFrame == anims[currentAnimation].numOfFrame - 1 &&
				waitDelay >= anims[currentAnimation].timeDelay[currentFrame])
			return true;
		return false;
	}
	
	
	//FOR MANY OBJECT
	//FOR MANY OBJECT
	//FOR MANY OBJECT
	//FOR MANY OBJECT


	public void setAnim(Actor actor,int id, boolean loop,boolean _showLastFrame) {
		actor._currentAnimation = id;
		actor._canLoop = loop;
		actor._ShowLastFrame = _showLastFrame;
		actor._waitDelay = 0;
		actor._currentFrame = 0;
	

	}
	public void drawAnim(Canvas g, int angle,int Scalex,int Scaley,Actor actor, int x, int y){
		if (actor._currentFrame < anims[actor._currentAnimation].numOfFrame)
			//drawAFrame(mainCanvas,0,20,1,1,200,200);
			drawAFrame(g, anims[actor._currentAnimation].arrayFrameIndex[actor._currentFrame],angle,Scalex,Scaley, x
					+ anims[actor._currentAnimation].postionX[actor._currentFrame], y
					+ anims[actor._currentAnimation].postionY[actor._currentFrame]);
		update( actor);
	}
	public void drawAnim_(Canvas g,Actor actor,int x, int y) {
		if (actor._currentFrame < anims[actor._currentAnimation].numOfFrame)
			drawAFrame(g, anims[actor._currentAnimation].arrayFrameIndex[actor._currentFrame], x
					+ anims[actor._currentAnimation].postionX[actor._currentFrame], y
					+ anims[actor._currentAnimation].postionY[actor._currentFrame]);
		update( actor );
	}

	public void drawAnim(Canvas g,int _currentAnimation,int _currentFrame, int _waitDelay) {
		if (_currentFrame < anims[_currentAnimation].numOfFrame)
			drawAFrame(g, anims[_currentAnimation].arrayFrameIndex[_currentFrame],
					xpos + anims[_currentAnimation].postionX[_currentFrame], ypos
							+ anims[_currentAnimation].postionY[_currentFrame]);
		else if (ShowLastFrame) {
			int frameIndex = anims[_currentAnimation].numOfFrame - 1;
			drawAFrame(g, anims[_currentAnimation].arrayFrameIndex[frameIndex],
					xpos + anims[_currentAnimation].postionX[frameIndex], ypos
							+ anims[_currentAnimation].postionY[frameIndex]);
		}

		update();
	}
	public void update(Actor actor) {
		actor._waitDelay++;
		if (actor._currentFrame <= anims[actor._currentAnimation].numOfFrame - 1) {
			if (actor._waitDelay > anims[actor._currentAnimation].timeDelay[actor._currentFrame]) {
				actor._currentFrame++;
				actor._waitDelay = 0;
			}
		}
		if (actor._currentFrame >= anims[actor._currentAnimation].numOfFrame) {
			if (actor._canLoop) {
				actor._currentFrame = 0;
				actor._waitDelay = 0;
			} else if (ShowLastFrame) {
				//	currentFrame = anims[currentAnimation].animFramesLen - 1;
			}
		}
	}	
		
		
	public boolean hasAnimationFinished(int _currentAnimation,int _currentFrame, int _waitDelay) {
		if (_currentFrame >= anims[_currentAnimation].numOfFrame)
			return true;
		if (_currentFrame == anims[_currentAnimation].numOfFrame - 1 &&
				_waitDelay >= anims[_currentAnimation].timeDelay[_currentFrame])
			return true;
		return false;
	}
	public static Bitmap loadImageFromAsset(String path) {
		AssetManager am = GameLib.mainGameLib.getAssets();
		// BufferedInputStream buf;
		// Bitmap bitmap = null;
		try {
			// buf = new BufferedInputStream(am.open(path));
			// bitmap = BitmapFactory.decodeStream(buf);
			return BitmapFactory.decodeStream(am.open(path));
			// buf.close();
		} catch (IOException e) {
			//
			e.printStackTrace();
		}
		return null;
		// return bitmap;
	}
	public static Point rotationPoint(int x, int y, int degreeAngle) { // width
		// oxy
// TODO Auto-generated method stub
double angle = (degreeAngle * Math.PI / 180);
double cosTheta = Math.cos(angle);
double sinTheta = Math.sin(angle);
double temp = (x * cosTheta - y * sinTheta);
if (temp > 0)
temp += 0.5;
else
temp -= 0.5;
int new_x = (int) temp;
temp = (x * sinTheta + y * cosTheta);
if (temp > 0)
temp += 0.5;
else
temp -= 0.5;
int new_y = (int) temp;
// System.out.println(new_x);
// System.out.print(new_y);

return new Point(new_x, new_y);

}
	//END FOR MANY OBJECT
}
