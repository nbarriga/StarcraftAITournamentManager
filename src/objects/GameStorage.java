package objects;

import java.util.Iterator;
import java.util.Vector;

import utility.ResultsParser;

public class GameStorage 
{
	private Vector<Game> allGames;
	
	private int currentGameIndex = 0;
	
	public GameStorage()
	{
		allGames = new Vector<Game>();
	}
	
	public void addGame(Game game, int round)
	{
		allGames.add(game);
	}
	
	public Game removePlayedGames(ResultsParser rp)
	{
		int maxId=-1;
		Game lastGame = null;
		Iterator<Game> it = allGames.iterator();
		while (it.hasNext()) 
		{
			Game g = it.next();
		    if (rp.hasGameResult(g.getGameID()))
		    {
				if(g.getGameID() > maxId)
				{
					maxId = g.getGameID();
					lastGame = g;
				}
		        it.remove();
		    }
		}
		return lastGame;
	}
	
	public boolean hasMoreGames()
	{
		return currentGameIndex < allGames.size();
	}
	
	public Game getNextGame()
	{
		Game g = allGames.get(currentGameIndex);
		currentGameIndex++;
		return g;
	}

	public Game lookupGame(int gameID, int round) 
	{
		for(Game g : allGames)
		{
			if(g.getGameID() == gameID)
			{
				return g;
			}
		}
		
		return null;
	}
}
