

function  toggleColors(id) {
    const color = ["red", "blue", "green", "yellow", "teal", "pink", "orange", "purple"]
    const player = document.getElementById(id)

    for (let i = 0; i < color.length; i++) {
        let pClass = player.className

        if (pClass.includes(color[i])) {
            if (i === 7) {
                player.className = pClass.replace(color[i], color[0])
                break;
            }
            player.className = pClass.replace(color[i], color[i + 1])
            break;
        }
    }
}