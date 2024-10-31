import { SingleStone } from "./SingleStone";

type Props = {
    nrOfStones: number
}

export const Stones = (props: Props) => {
    const { nrOfStones } = props;
    const range = Array.from(Array(nrOfStones).keys())

    function getRandomDir(): boolean {
        return Math.random() > 0.5
    }

    return (
        <>
            {range.map((i) => <SingleStone key={i} left={getRandomDir()} top={getRandomDir()} />)}
        </>
    )
}