import classNames from "classnames";
import { useMancalaGame } from "../contexts/MancalaGameContext"
import { RevengeButton } from "./RevengeButton";

export const WinnerMessage = () => {
    const { gameState } = useMancalaGame();

    return (
        <>
            <div
                className={classNames(
                    "text-center",
                    "text-3xl",
                    "text-amber-900",
                    "leading-[4rem]",
                )}
            >
                {gameState?.gameStatus.winner} has won! Congratulations!<br/>
                <RevengeButton />
            </div>
        </>
    )
}