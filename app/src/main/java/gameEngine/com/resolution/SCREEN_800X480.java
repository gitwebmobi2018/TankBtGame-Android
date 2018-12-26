package gameEngine.com.resolution;

import com.battle.tanks90.MainActivity;

public class SCREEN_800X480 {
	public static void setInterface() {

		MainActivity.nScaleX = (800 * 1.0f) / 1024;
		MainActivity.nScaleY = (480 * 1.0f) / 600;
		// SPRITE FILE
		DEF.FILE_BACKDROUND_LOADING = "image/background.png";
		DEF.FILE_NAME_MENU_BACKDROUND_MENU = "image/bgmenu_800x480_new.png";
		DEF.FILE_NAME_MENU_BACKDROUND_GAMEPLAY = "image/bggameplay_800x480_new.png";
		// INDEX FRAME
		DEF.INDEX_FRAME_LOCK = DEF.INDEX_FRAME_LOCK;
		DEF.INDEX_FRAME_ARROW_LEFT = DEF.INDEX_FRAME_ARROW_LEFT;
		DEF.INDEX_FRAME_ARROW_RIGHT = DEF.INDEX_FRAME_ARROW_RIGHT;
		DEF.INDEX_FRAME_GAME_TITLE = DEF.INDEX_FRAME_GAME_TITLE;

		DEF.INDEX_FRAME_GAME_TITLE_2 = DEF.INDEX_FRAME_GAME_TITLE_2;// using in game menu
		DEF.INDEX_FRAME_TANK = DEF.INDEX_FRAME_TANK;
		DEF.FRAME_SOUND_BEGIN_INDEX = DEF.FRAME_SOUND_BEGIN_INDEX;		
		// LOGO
		DEF.LOGO_ICON_OFFSET_Y = 100;
		DEF.SPRITE_TITLE_ANIM = 0;
		DEF.LOGO_GAME_TILE_X = 130;
		DEF.LOGO_GAME_TILE_Y = 230;
		// COMMON
		DEF.ARROW_WIDTH =80;
		DEF.ARROW_HEIGH = 90;
		DEF.SOUND_OPTION_X = 700;
		DEF.SOUND_OPTION_Y = 8;


		DEF.GAME_TITLE_X =  MainActivity.SCREEN_WIDTH/2;
		DEF.GAME_TITLE_Y = 35;
		// MAIN MENU

		DEF.LOGO_GAME_TITLE_X = 100;
		DEF.LOGO_GAME_TITLE_Y = 26;

		DEF.MENU_MAIN_TOP_X = 120;
		DEF.MENU_MAIN_TOP_Y = 175;
		DEF.MENU_ELEMENT_WIDTH = 180;
		DEF.MENU_ELEMENT_HEIGHT = 55;
		DEF.MENU_ELEMENT_SPACE = 10;
		DEF.MENU_TOUCH_OFFET_X = -100;
		DEF.MENU_TOUCH_OFFET_Y = -27;
		DEF.MENU_TEXT_OFFSET_X = 0;
		DEF.MENU_TEXT_OFFSET_Y = 0;
		DEF.GAME_TANK_X = 500;
		DEF.GAME_TANK_Y = 300;

		// SELECT LEVEL MENU
		DEF.MENU_LEVEL_BEGIN_X = 340;
		DEF.MENU_LEVEL_BEGIN_Y = 140;
		DEF.MENU_LEVEL_WIDTH = 140;
		DEF.MENU_LEVEL_HEIGHT = 40;
		DEF.MENU_LEVEL_SPACE_X = 165;
		DEF.MENU_LEVEL_SPACE_Y = 66;
		
		DEF.SELECT_LEVEL_ARROW_RIGHT_X = 690;
		DEF.SELECT_LEVEL_ARROW_LEFT_X = 200;
		DEF.SELECT_LEVEL_ARROW_Y = 240;
		// GAMEPLAY
		DEF.BOARD_MAP_OFFSET_X = 227;
		DEF.BOARD_MAP_OFFSET_Y = 43;
		DEF.MENU_GAME_PLAY_TOP_X = 110;
		DEF.MENU_GAME_PLAY_TOP_Y = 40;
		//LOSE
		DEF.MENU_WIN_LOSE_TITLE = MainActivity.SCREEN_HEIGHT/4 ;
		DEF.MENU_LOSE_RESTART_X = MainActivity.SCREEN_WIDTH/2 - 200;
		DEF.MENU_LOSE_MAINMENU_X = MainActivity.SCREEN_WIDTH/2;
		DEF. MENU_LOSE_EXIT_X = MainActivity.SCREEN_WIDTH/2 + 200;
		DEF.MENU_LOSE_Y = MainActivity.SCREEN_HEIGHT*3/4;
		//WIN
		DEF.MENU_WIN_NEXT_LEVEL_X = MainActivity.SCREEN_WIDTH/2 - 200;
		DEF.MENU_WIN_MAINMENU_X =  MainActivity.SCREEN_WIDTH/2;
		DEF.MENU_WIN_EXIT_X = MainActivity.SCREEN_WIDTH/2+ 200;
		DEF.MENU_WIN_Y = MainActivity.SCREEN_HEIGHT*3/4 ;	
		// GAME INFO IN GAME PLAY
		DEF.GAME_INFO_TANK_NPC_POS_X = 670;
		DEF.GAME_INFO_TANK_NPC_POS_Y = 100;
		DEF.GAME_INFO_MY_TANK_LIFE_POS_X = 690;
		DEF.GAME_INFO_MY_TANK_LIFE_POS_Y = 240;
		DEF.GAME_INFO_CURRENT_LEVEL_POS_X = 690;
		DEF.GAME_INFO_CURRENT_LEVEL_POS_Y = 275;			
		// HELP SCREEN
		DEF.HELP_POS_ARROW_LEFT_X = 340;
		DEF.HELP_POS_ARROW_Y = 350;
		DEF.HELP_POS_ARROW_RIGHT_X = 590;
		DEF.HELP_DETAIL_X = 520;
		DEF.HELP_DETAIL_Y = 140;

	}
}
