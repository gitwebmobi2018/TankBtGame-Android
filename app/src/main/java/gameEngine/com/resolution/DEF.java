package gameEngine.com.resolution;

import com.battle.tanks90.MainActivity;

//default to 1024x600<<=>> KINDLE FIRE(AMAZONE)
public class DEF {

	// IMAGE FILE
	public static String FILE_BACKDROUND_LOADING = "image/background.png";
	public static String FILE_NAME_MENU_BACKDROUND_MENU = "image/bgmenu.png";
	public static String FILE_NAME_MENU_BACKDROUND_GAMEPLAY = "image/bggameplay.png";
	// INDEX FRAME
	public static int INDEX_FRAME_LOCK = 2;
	public static int INDEX_FRAME_ARROW_LEFT = 3;
	public static int INDEX_FRAME_ARROW_RIGHT = 5;
	public static int INDEX_FRAME_GAME_TITLE = 13;

	public static int INDEX_FRAME_GAME_TITLE_2 = 19;// using in game menu
	public static int INDEX_FRAME_TANK = 14;
	public static int FRAME_SOUND_BEGIN_INDEX = 15;
	// LOGO
	public static int LOGO_ICON_OFFSET_Y = 200;// logo caogia
	public static int SPRITE_TITLE_ANIM = 0;// xem trong sprite menu.sprt
	public static int LOGO_GAME_TILE_X = 230;
	public static int LOGO_GAME_TILE_Y = 330;
	// COMMON
	public static int ARROW_WIDTH = 80;
	public static int ARROW_HEIGH = 90;
	public static int SOUND_OPTION_X = MainActivity.SCREEN_WIDTH - 100;
	public static int SOUND_OPTION_Y = 10;
	
	public static int GAME_TITLE_X = MainActivity.SCREEN_WIDTH/2;
	public static int GAME_TITLE_Y = 35;

	// MAIN MENU

	public static int LOGO_GAME_TITLE_X = 120;
	public static int LOGO_GAME_TITLE_Y = 45;

	public static int MENU_MAIN_TOP_X = 120;
	public static int MENU_MAIN_TOP_Y = 180;
	public static int MENU_ELEMENT_WIDTH = 180;
	public static int MENU_ELEMENT_HEIGHT = 65;
	public static int MENU_ELEMENT_SPACE = 8;
	public static int MENU_TOUCH_OFFET_X = -100;
	public static int MENU_TOUCH_OFFET_Y = -27;
	public static int MENU_TEXT_OFFSET_X = 0;
	public static int MENU_TEXT_OFFSET_Y = 0;
	public static int GAME_TANK_X = 610;
	public static int GAME_TANK_Y = 380;
	// SELECT LEVEL MENU
	public static int MENU_LEVEL_BEGIN_X = 512;
	public static int MENU_LEVEL_BEGIN_Y = 256;
	public static int MENU_LEVEL_WIDTH = 140;
	public static int MENU_LEVEL_HEIGHT = 40;
	public static int MENU_LEVEL_SPACE_X = 190;
	public static int MENU_LEVEL_SPACE_Y = 70;	
	
	public static int SELECT_LEVEL_ARROW_RIGHT_X = 1024;
	public static int SELECT_LEVEL_ARROW_LEFT_X = 256;
	public static int SELECT_LEVEL_ARROW_Y = 312;
	// GAMEPLAY
	public static int BOARD_MAP_OFFSET_X = 320;
	public static int BOARD_MAP_OFFSET_Y = 101;
	public static int MENU_GAME_PLAY_TOP_X = 160;
	public static int MENU_GAME_PLAY_TOP_Y = 140;
	//LOSE
	public static int MENU_WIN_LOSE_TITLE = MainActivity.SCREEN_HEIGHT/4 ;
	public static int MENU_LOSE_RESTART_X = MainActivity.SCREEN_WIDTH/2 - 200;
	public static int MENU_LOSE_MAINMENU_X = MainActivity.SCREEN_WIDTH/2;
	public static int MENU_LOSE_EXIT_X = MainActivity.SCREEN_WIDTH/2 + 200;
	public static int MENU_LOSE_Y = MainActivity.SCREEN_HEIGHT*3/4;
	//WIN
	public static int MENU_WIN_NEXT_LEVEL_X = MainActivity.SCREEN_WIDTH/2 - 200;
	public static int MENU_WIN_MAINMENU_X =  MainActivity.SCREEN_WIDTH/2;
	public static int MENU_WIN_EXIT_X = MainActivity.SCREEN_WIDTH/2+ 200;
	public static int MENU_WIN_Y = MainActivity.SCREEN_HEIGHT*3/4 ;	

	// GAME INFO IN GAME PLAY
	public static int GAME_INFO_TANK_NPC_POS_X = 810;
	public static int GAME_INFO_TANK_NPC_POS_Y = 120;
	public static int GAME_INFO_MY_TANK_LIFE_POS_X = 810;
	public static int GAME_INFO_MY_TANK_LIFE_POS_Y = 250;
	public static int GAME_INFO_CURRENT_LEVEL_POS_X = 810;
	public static int GAME_INFO_CURRENT_LEVEL_POS_Y = 320;	
	// HELP SCREEN
	public static int HELP_POS_ARROW_LEFT_X = 440;
	public static int HELP_POS_ARROW_RIGHT_X = 690;
	public static int HELP_POS_ARROW_Y = 440;
	public static int HELP_DETAIL_X = 590;
	public static int HELP_DETAIL_Y = 180;

}
