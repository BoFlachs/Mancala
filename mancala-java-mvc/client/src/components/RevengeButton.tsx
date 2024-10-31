import classNames from "classnames"
import { startGame } from "../services/api";
import { isGameState } from "../types";
import { useMancalaGame } from "../contexts/MancalaGameContext";


export const RevengeButton = () => {
    const { gameState, setGameState } = useMancalaGame();
    const player1 = gameState!.players[0].name;
    const player2 = gameState!.players[1].name;
    
    const onRevenge = async () => {
        const result = await startGame(player2, player1);

        if (isGameState(result)) {
            setGameState(result);
            location.href = "/"
        } 
    }

    return (
            <button
                type="button"
                className={classNames(
                    "rounded-full",
                    "bg-amber-900",
                    "px-7",
                    "pb-[8px]",
                    "pt-[10px]",
                    "text-sm",
                    "font-bold",
                    "leading-normal",
                    "text-neutral-50",
                    "transition duration-300",
                    "ease-in-out",
                    "hover:text-neutral-800",
                    "hover:bg-yellow-800",
                    "hover:bg-opacity-90",
                )}
                onClick={onRevenge}
                >
                PLAY AGAIN <br /> ({player2} now goes first)
            </button >
    )
}

