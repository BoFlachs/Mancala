import classNames from "classnames";
import { useMancalaGame } from "../contexts/MancalaGameContext";
import { Kalaha } from "./Kalaha";
import { PitButton } from "./PitButton";

export const MancalaBoard = () => {
    const { gameState } = useMancalaGame();

    const playerOneBowls = gameState?.players[0].pits.map(
        (_pit, index) => index != 6 ? <PitButton key={10 + index}
            playerIndex={0}
            pitIndex={index} /> :
            <Kalaha key={index} playerIndex={0} kalahaIndex={6} /> )

    return (
        <div
            className={classNames(
                "bg-yellow-600",
                "bg-board",
                "bg-blend-color-burn",
                "grid",
                "grid-cols-10",
                "w-[100%]",
                "h-[250px]",
                "rounded-[100px]",
                "m-auto",
                "border-4",
                "border-yellow-800",
                "col-start-1",
                "row-start-1",
                "col-span-6",
                "row-span-4"
            )}
        >
                {gameState?.players[1].pits.map(
                    (_pit, index) => index != 6 ? <PitButton key={20 + index}
                    playerIndex={1}
                    pitIndex={index} /> :
                    <Kalaha key={index} playerIndex={1} kalahaIndex={6} />
                ).reverse()}
                {playerOneBowls![6]}
                {playerOneBowls?.slice(0,6)}
        </div>
    )


}